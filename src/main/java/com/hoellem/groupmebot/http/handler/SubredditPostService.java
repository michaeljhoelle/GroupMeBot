package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.client.RedditClient;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import com.hoellem.groupmebot.model.reddit.Subreddit;
import com.hoellem.groupmebot.model.reddit.SubredditResponseWrapper;
import com.hoellem.groupmebot.model.repository.SubredditUrl;
import com.hoellem.groupmebot.repository.SubredditUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubredditPostService implements RequestHandler
{
  private final GroupMeMessenger messenger;
  private final RedditClient redditClient;
  private final SubredditUrlRepository subredditUrlRepository;

  public void handle(GroupMeRequest request)
  {
    Matcher matcher = requestPattern.matcher(request.getText());
    if (matcher.find())
    {
      Subreddit subreddit = Subreddit.fromShortName(matcher.group(1));
      sendImageUrl(subreddit);
    }
  }

  private void sendImageUrl(Subreddit subreddit) {
    SubredditResponseWrapper wrapper;
    switch (subreddit) {
      case SUFFER:
        wrapper = redditClient.getHotSuffering();
        break;
      case BEANS:
        wrapper = redditClient.getHotBeans();
        break;
      default:
        wrapper = null;
    }
    if (wrapper != null) {
      List<SubredditResponseWrapper> posts = wrapper.getData().getChildren();
      List<String> urls = posts.stream()
              .filter(post -> StringUtils.isEmpty(post.getData().getSelftext()))
              .map(post -> post.getData().getUrl())
              .collect(Collectors.toList());
      Collections.shuffle(urls);
      for (String url : urls) {
        Optional<SubredditUrl> subredditUrlOptional = subredditUrlRepository.findById(url);
        if (subredditUrlOptional.isPresent()) {
          SubredditUrl subredditUrl = subredditUrlOptional.get();
          if (subredditUrl.getTimestamp().isBefore(LocalDateTime.now().minusDays(1))) {
            subredditUrl.setTimestamp(LocalDateTime.now());
            postMessages(posts, url);
            return;
          }
        } else {
          subredditUrlRepository.save(new SubredditUrl(url, LocalDateTime.now()));
          postMessages(posts, url);
          return;
        }
      }
    }
  }

  private void postMessages(List<SubredditResponseWrapper> posts, String url) {
    String title = posts.stream().filter(post -> url.equals(post.getData().getUrl())).findFirst().get().getData().getTitle();
    messenger.sendGroupMeMessage(title);
    messenger.sendGroupMeMessage(url);
  }

}
