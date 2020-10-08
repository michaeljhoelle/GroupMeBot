package com.hoellem.groupmebot.http.datasource.user;

import com.hoellem.groupmebot.GroupMeBotApplication;
import com.hoellem.groupmebot.http.datasource.group.Group;
import com.hoellem.groupmebot.http.datasource.group.GroupRepository;
import com.hoellem.groupmebot.http.groupme.GroupMeMessenger;
import com.hoellem.groupmebot.http.groupme.getgroup.FindAllGroupsResponse;
import com.hoellem.groupmebot.http.groupme.getgroup.GroupDetails;
import com.hoellem.groupmebot.http.groupme.getgroup.GroupMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
  protected static final Logger logger = LoggerFactory.getLogger(GroupMeBotApplication.class);
  private UserRepository userRepository;
  private GroupRepository groupRepository;
  private GroupMeMessenger groupMeMessenger;

  @Autowired
  public void setUserRepository(UserRepository userRepository)
  {
    this.userRepository = userRepository;
  }

  @Autowired
  public void setGroupRepository(GroupRepository groupRepository)
  {
    this.groupRepository = groupRepository;
  }

  @Autowired
  public void setGroupMeMessenger(GroupMeMessenger groupMeMessenger)
  {
    this.groupMeMessenger = groupMeMessenger;
  }

  @Transactional
  public void updateGroupMeUsers() {
    int page = 1;
    while (true)
    {
      ResponseEntity<FindAllGroupsResponse> response = groupMeMessenger.fetchAllGroupDetails(page++);
      if (response.getBody() != null)
      {
        List<GroupDetails> groups = response.getBody().getGroupDetailsList();
        if (groups.size() == 0)
        {
          break;
        }
        for (GroupDetails group : groups)
        {
          updateGroup(group);
          updateUsers(group);
        }
      }
    }
  }

  @Transactional
  public void incrementFeetCount(int userId)
  {
    userRepository.findById(userId).ifPresent(User::incrementFeetCount);
  }

  @Transactional
  public String getFeetCounts()
  {
    List<User> topFeetUsers = userRepository.getTopFeetCounts();
    StringBuilder builder = new StringBuilder();
    for (User user: topFeetUsers)
    {
      builder.append(String.format( "%-21s %3s\n", user.getName(), user.getFeetCount() ));
    }
    return builder.toString().replaceAll(" ", "_");
  }

  public void updateUsers(GroupDetails groupDetails)
  {
    logger.info("\nUpdating users from " + groupDetails.getName());
    List<User> newUserList = new ArrayList<>();

    Optional<Group> group = groupRepository.getById(groupDetails.getGroupId());

    for (GroupMember groupMember : groupDetails.getMembers())
    {
      Optional<User> user = userRepository.getById(groupMember.getUserId());
      if (user.isPresent())
      {
        user.map(target ->
        {
          target.addGroup(group.orElse(null));
          target.setName(groupMember.getName());
          target.setNickname(groupMember.getNickname());
          return target;
        });
      }
      else
      {
        newUserList.add(new User(groupMember.getUserId(), groupMember.getName(), groupMember.getNickname(), group.orElse(null)));
      }
    }
    userRepository.saveAll(newUserList);
  }

  public void updateGroup(GroupDetails groupDetails)
  {
    Optional<Group> group = groupRepository.findById(groupDetails.getGroupId());
    if (group.isPresent())
    {
      group.map(target ->
      {
        target.setName(groupDetails.getName());
        return target;
      });
    }
    else
    {
      groupRepository.save(new Group(groupDetails.getGroupId(), groupDetails.getName()));
    }
  }
}
