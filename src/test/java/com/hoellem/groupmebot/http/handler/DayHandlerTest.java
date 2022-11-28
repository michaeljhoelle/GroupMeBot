package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DayHandlerTest {

    @InjectMocks
    DayHandler dayHandler;

    @Mock
    GroupMeMessenger messenger;

    @Test
    void validCurrentTimes() {
        dayHandler.validCurrentTimes().forEach(System.out::println);
    }

    @Test
    void handleNoQuestionMark() {
        String testText = "the " + LocalDate.now().getDayOfMonth() + dayHandler.getDayOfMonthSuffix(LocalDate.now().getDayOfMonth());
        dayHandler.handle(new GroupMeRequest().setText("is it " + testText));
        verify(messenger, times(1)).sendGroupMeMessage("Yes");
    }

    @Test
    void handleWithQuestionMark() {
        String testText = "the " + LocalDate.now().getDayOfMonth() + dayHandler.getDayOfMonthSuffix(LocalDate.now().getDayOfMonth());
        dayHandler.handle(new GroupMeRequest().setText("is it " + testText + "?"));
        verify(messenger, times(1)).sendGroupMeMessage("Yes");
    }
}