package com.hoellem.groupmebot.http.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DayHandlerTest {

    @InjectMocks
    DayHandler dayHandler;

    @Test
    void validCurrentTimes() {
        dayHandler.validCurrentTimes().forEach(System.out::println);
    }
}