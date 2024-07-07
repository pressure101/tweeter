package com.tweeter.demo.DataConfig;

// import org.springframework.data.jpa.repository.JpaRepository;
import com.tweeter.demo.DataConfig.User;
import java.util.List;

import org.springframework.stereotype.Component;

// public interface UserRepository extends JpaRepository<User, String> {
    
// }
@Component
public interface UserRepository {
    List<User> findAll();
}
