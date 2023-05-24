package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.http.RequestHandler;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static com.hoellem.groupmebot.util.ResponseUtils.MIKES_USER_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebugDayHandler implements RequestHandler {
    private final GroupMeMessenger messenger;
    private final DayHandler dayHandler;

    @Override
    public void handle(GroupMeRequest request) {
        if (request.getUserId().equals(MIKES_USER_ID)) {
            String responseText = String.join("\n", new HashSet<>(dayHandler.validCurrentTimes()));
            messenger.sendGroupMeMessage(responseText);
        }
    }
}
