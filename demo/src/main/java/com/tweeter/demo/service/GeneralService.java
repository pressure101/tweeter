package com.tweeter.demo.service;

import com.tweeter.demo.repository.Tweets;
import com.tweeter.demo.repository.TweetsRepository;
import com.tweeter.demo.repository.User;
import com.tweeter.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralService {

    private final TweetsRepository tweetsRepository;
    private final UserRepository userRepository;

    public GeneralService(TweetsRepository tweetsRepository, UserRepository userRepository) {
        this.tweetsRepository = tweetsRepository;
        this.userRepository = userRepository;
    }

    public List<Tweets> getTweets() {
       return tweetsRepository.findAll();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
