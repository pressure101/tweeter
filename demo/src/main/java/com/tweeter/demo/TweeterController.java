package com.tweeter.demo;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

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
        User loggedInUser = new User();
        model.addAttribute("username", user.username);
        
        try {
        // loop through users in db --> max 5 users
        userList = userRepo.findAll();
        for(User loopedUser: userList) {
            if(loopedUser.username.equals(user.username)) {
                loggedInUser = loopedUser;
            }
        }
        // check if the above loop found a user, if not throw error page
        if(loggedInUser.username == null){
            return "error";
        }

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
    public ModelAndView getAllTweets(Model model) {
        // logic to grab all tweets from db
        
        ModelAndView mav = new ModelAndView("tweets");
        mav.addObject("tweets", tweetsRepo.findAll());
        model.addAttribute("username", currentUsername);
        System.out.println("Hi " + currentUsername);
        return mav;
    }

    @GetMapping("/feed") 
    public ModelAndView getUserTweets(Model model) {
        ModelAndView mav = new ModelAndView("myTweets");
        
        List<Tweets> allTweets = tweetsRepo.findAll();
        List<Tweets> filteredTweets = new ArrayList<Tweets>();
        // filter out not applicable tweets
        for(Tweets tweet: allTweets){
            if(tweet.getUsername().equals(currentUsername)){
                filteredTweets.add(tweet);
            }
        }
        mav.addObject("filteredTweets", filteredTweets);
        model.addAttribute("username", currentUsername);
        System.out.println("Hi " + currentUsername);
        return mav;
    }

    @PostMapping("/postTweet")
    public String postTweet(String content, Model model) {
        model.addAttribute("username", currentUsername);
        // logic to insert into db
        System.out.println(content);
        Tweets tweet =  new Tweets();
        tweet.setContent(content);
        tweet.setTimeTweeted(new Date());
        tweet.setUsername(currentUsername);
        tweetsRepo.save(tweet);
        return "confirmation";

    }


     
}
