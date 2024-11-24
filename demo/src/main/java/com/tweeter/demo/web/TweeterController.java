package com.tweeter.demo.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tweeter.demo.dto.Tweet;
import com.tweeter.demo.repository.TweetEntity;
import com.tweeter.demo.service.GeneralService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class TweeterController {

    private final String currentUsername = null;

    private final GeneralService generalService;

    /* Initial endpoints to get application functional for demo */
    @GetMapping("/hello-pressure")
    public String helloPressure() {
        return "hey you";
    }

    @GetMapping()
    public List<TweetEntity> getTweets() {
        return generalService.getTweets();
    }

    @PostMapping()
    void createTweet(@RequestBody CreateTweetRequest request) {
        generalService.createTweet(Tweet.builder()
                .username(request.getUsername())
                .content(request.getContent())
                .build());
    }

    /* END TEMP ENDPOINTS */

    /* TODO: commenting out for initial rewrite but may need reference for later
//    @GetMapping("/")
//    public String loginPage(Model model) {
//        model.addAttribute("user", new User());
//        return "hello bitch";
//        //return generalService.getTweets().getFirst().getUsername();
//    }

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

        } catch (Exception e ){
            return "error";
        }
        if(!loggedInUser.getPassword().equals(user.getPassword())){
            return "error";
        }
        //currentUsername = user.username;
        return "home";
    }

     */

    /* Endpoints below shouldn't interfere with demo so leaving uncommented for now */

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
        List<TweetEntity> filteredTweets = new ArrayList<TweetEntity>();
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
        TweetEntity tweet =  new TweetEntity();
        tweet.setContent(content);
        tweet.setTimeTweeted(new Date());
        tweet.setUsername(currentUsername);
        // tweetsRepo.save(tweet);
        return "confirmation";

    }


     
}
