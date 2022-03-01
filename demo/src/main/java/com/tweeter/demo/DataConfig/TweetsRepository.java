package com.tweeter.demo.DataConfig;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetsRepository extends JpaRepository<Tweets, String> {
}
