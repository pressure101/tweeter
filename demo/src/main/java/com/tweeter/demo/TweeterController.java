package com.tweeter.demo;

import java.util.List;
import java.util.Optional;

import com.tweeter.demo.DataConfig.TweetsRepository;
import com.tweeter.demo.DataConfig.User;
import com.tweeter.demo.DataConfig.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.persistence.EntityNotFoundException;


@Controller
public class TweeterController {

    

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
    public String submitLogin(User user) {
        List<User> userList;
        User loggedInUser;
        
        try {
        userList = userRepo.findAll();
        //System.out.println(userList);
        loggedInUser = userList.get(0);

        System.out.println(loggedInUser.getPassword());
        System.out.println(user.getPassword());

        } catch (EntityNotFoundException e ){
            return "error";
        }
        if(!loggedInUser.getPassword().equals(user.getPassword())){
            return "error";
        }
        return "home";
    }

    @PostMapping("/feed/{username}/all") 
    public String getAllTweets(@PathVariable String username) {
        
        System.out.println(username);
        return "tweets";
    }

    @PostMapping("/feed/{username}") 
    public String getUserTweets(@PathVariable String username) {

        System.out.println(username);
        return "tweets";
    }

    @PostMapping("/feed/{username}/postTweet")
    public void postTweet(@PathVariable String username){
        // logic to insert into db

        // end here and return all tweets with reflected new tweet
        getAllTweets(username);

    }


     
}
