package com.tweeter.demo.service;

import com.tweeter.demo.dto.Tweet;
import com.tweeter.demo.repository.TweetEntity;
import com.tweeter.demo.repository.TweetsRepository;
import com.tweeter.demo.repository.User;
import com.tweeter.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetsService {

    private final TweetsRepository tweetsRepository;
    private final UserRepository userRepository;

    public TweetsService(TweetsRepository tweetsRepository, UserRepository userRepository) {
        this.tweetsRepository = tweetsRepository;
        this.userRepository = userRepository;
    }

    public List<TweetEntity> getTweets() {
       return tweetsRepository.findAll();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void createTweet(Tweet tweet) {
        validateContent(tweet.getContent());

        var tweetEntity = new TweetEntity(); // doing this instead of builder so default work on time field
        tweetEntity.setUsername(tweet.getUsername());
        tweetEntity.setContent(tweet.getContent());

        tweetsRepository.save(tweetEntity);
    }

    private void validateContent(String content) {
        // TODO do some sanity checking of content
    }
}
