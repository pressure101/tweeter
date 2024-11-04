package com.tweeter.demo.service;

import com.tweeter.demo.repository.Tweets;
//import com.tweeter.demo.repository.TweetsRepository;
import com.tweeter.demo.repository.User;
//import com.tweeter.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GeneralService {

//    private final TweetsRepository tweetsRepository;
//    private final UserRepository userRepository;

    public List<Tweets> getTweets() {
        Tweets tweet = new Tweets();
        tweet.setUsername("Lau Lawless");
        return List.of(tweet);
       //return tweetsRepository.findAll();
    }

    public List<User> getUsers() {
        return List.of(new User());
        //return userRepository.findAll();
    }
}
