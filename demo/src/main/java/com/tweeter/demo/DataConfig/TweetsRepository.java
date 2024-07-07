package com.tweeter.demo.DataConfig;

//import org.springframework.data.jpa.repository.JpaRepository;
import com.tweeter.demo.DataConfig.Tweets;
import java.util.List;

import org.springframework.stereotype.Component;

// public interface TweetsRepository extends JpaRepository<Tweets, String> {
// }
@Component
public interface TweetsRepository {
    Tweets save(Tweets tweet);
    List<Tweets> findAll();
}