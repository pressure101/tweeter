package com.tweeter.demo;

import java.util.Date;
import java.util.List;

import com.tweeter.demo.DataConfig.Tweets;
import com.tweeter.demo.DataConfig.TweetsRepository;
import com.tweeter.demo.DataConfig.User;
import com.tweeter.demo.DataConfig.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.EntityNotFoundException;


@Controller
public class TweeterController {

    String currentUsername;

    @Autowired
    private TweetsRepository tweetsRepo;

    @Autowired
    private UserRepository userRepo;
    
    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/") 
    public String submitLogin(Model model, User user) {
        List<User> userList;
        User loggedInUser;
        model.addAttribute("username", user.username);
        
        try {
        userList = userRepo.findAll();
        loggedInUser = userList.get(0);

        System.out.println(loggedInUser.getPassword());
        System.out.println(user.getPassword());

        } catch (EntityNotFoundException e ){
            return "error";
        }
        if(!loggedInUser.getPassword().equals(user.getPassword())){
            return "error";
        }
        currentUsername = user.username;
        return "home";
    }

    @GetMapping("/feed/all") 
    public String getAllTweets(Model model) {
        // logic to grab all tweets from db
        model.addAttribute("username", currentUsername);
        System.out.println("Hi " + currentUsername);
        return "tweets";
    }

    @GetMapping("/feed") 
    public String getUserTweets(Model model) {
        // logic to grab users tweets
        model.addAttribute("username", currentUsername);
        System.out.println("Hi " + currentUsername);
        return "myTweets";
    }

    @PostMapping("/postTweet")
    public String postTweet(@ModelAttribute String content, Model model) {
        model.addAttribute("username", currentUsername);
        // logic to insert into db
        Tweets tweet =  new Tweets();
        tweet.setContent(content);
        tweet.setTimeTweeted(new Date());
        tweet.setUsername(currentUsername);
        tweetsRepo.save(tweet);
        return "confirmation";

    }


     
}
