package com.tweeter.demo.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tweeter.demo.repository.Tweets;
import com.tweeter.demo.repository.User;
import com.tweeter.demo.service.GeneralService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

// import jakarta.persistence.EntityNotFoundException;


@RestController
@AllArgsConstructor
public class TweeterController {

    private final String currentUsername = null;

    private final GeneralService generalService;

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return generalService.getTweets().getFirst().getUsername();
    }

    @GetMapping("/hello-cavasier")
    public String sayHelloCav() {

        return "hey you";
    }

    @PostMapping("/") 
    public String submitLogin(Model model, User user) {
        List<User> userList;
        User loggedInUser = new User();
        model.addAttribute("username", user.username);
        
        try {
        // loop through users in db --> max 5 users
        // userList = userRepo.findAll();
//        for(User loopedUser: userList) {
//            if(loopedUser.username.equals(user.username)) {
//                loggedInUser = loopedUser;
//            }
//        }
        // check if the above loop found a user, if not throw error page
        if(loggedInUser.username == null){
            return "error";
        }

        System.out.println(loggedInUser.getPassword());
        System.out.println(user.getPassword());

        } catch (Exception e ){
            return "error";
        }
        if(!loggedInUser.getPassword().equals(user.getPassword())){
            return "error";
        }
        //currentUsername = user.username;
        return "home";
    }

    @GetMapping("/feed/all") 
    public ModelAndView getAllTweets(Model model) {
        // logic to grab all tweets from db
        
        ModelAndView mav = new ModelAndView("tweets");
        //mav.addObject("tweets", tweetsRepo.findAll());
        model.addAttribute("username", currentUsername);
        System.out.println("Hi " + currentUsername);
        return mav;
    }

    @GetMapping("/feed") 
    public ModelAndView getUserTweets(Model model) {
        ModelAndView mav = new ModelAndView("myTweets");
        
        //List<Tweets> allTweets = tweetsRepo.findAll();
        List<Tweets> filteredTweets = new ArrayList<Tweets>();
        // filter out all tweets not owned by current user signed in
//        for(Tweets tweet: allTweets){
//            if(tweet.getUsername().equals(currentUsername)){
//                filteredTweets.add(tweet);
//            }
//        }
        mav.addObject("filteredTweets", filteredTweets);
        model.addAttribute("username", currentUsername);
        System.out.println("Hi " + currentUsername);
        return mav;
    }

    @PostMapping("/postTweet")
    public String postTweet(String content) {
        // logic to insert into db
        System.out.println(content);
        Tweets tweet =  new Tweets();
        tweet.setContent(content);
        tweet.setTimeTweeted(new Date());
        tweet.setUsername(currentUsername);
        // tweetsRepo.save(tweet);
        return "confirmation";

    }


     
}
