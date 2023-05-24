package com.hoellem.groupmebot.http.handler;

import com.hoellem.groupmebot.client.GroupMeMessenger;
import com.hoellem.groupmebot.model.groupme.GroupMeRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
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

    @Test
    void handleNotToday() {
        String testText = "the " + LocalDate.now().getDayOfMonth() + dayHandler.getDayOfMonthSuffix(LocalDate.now().getDayOfMonth());
        dayHandler.handle(new GroupMeRequest().setText("is it another day?"));
        verify(messenger, times(1)).sendGroupMeMessage("No");
    }

    @Test
    void testValidCurrentTimes() {
        ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.of("-05:00"));
        now = ZonedDateTime.now(ZoneId.of("America/New_York"));
        log.info(String.format("%02d", now.getHour()) + ":" + String.format("%02d", now.getMinute()));
    }
}