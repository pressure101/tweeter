package com.tweeter.demo.service;

import com.tweeter.demo.dto.Tweet;
import com.tweeter.demo.repository.TweetEntity;
import com.tweeter.demo.repository.TweetsRepository;
import com.tweeter.demo.repository.User;
import com.tweeter.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetsServiceTest {
    @Mock
    TweetsRepository tweetsRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    TweetsService fixture;

    @Test
    void getTweets_happyPath() {
        var tweetEntity = TweetEntity.builder().username("Bill Nye").build();
        when(tweetsRepository.findAll()).thenReturn(List.of(tweetEntity));

        var response = fixture.getTweets();

        assertEquals(1, response.size());
        assertEquals("Bill Nye", response.getFirst().getUsername());
    }

    @Test
    void getUsers_happyPath() {
        var user = new User("Bill Nye", "I'm the coolest science teacher");
        when(userRepository.findAll()).thenReturn(List.of(user));

        var response = fixture.getUsers();

        assertEquals(1, response.size());
        assertEquals("Bill Nye", response.getFirst().getUsername());
    }

    @Test
    void createTweet_happyPath() {
        var tweet = Tweet.builder().username("Bill Nye").build();
        var tweetEntity = new TweetEntity();
        tweetEntity.setUsername("Bill Nye");

        when(tweetsRepository.save(any())).thenReturn(tweetEntity);

        fixture.createTweet(tweet);
    }
}