package com.tweeter.demo.DataConfig;

import java.util.Date;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "Tweets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweets {
    
    // @Id
    // @Column(length = 100)
    // @GeneratedValue
    private Integer id;
    private String username;
    private String content;
    private Date timeTweeted;

    public Integer getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeTweeted() {
        return timeTweeted;
    }

    public void setTimeTweeted(Date timeTweeted) {
        this.timeTweeted = timeTweeted;
    }
}
