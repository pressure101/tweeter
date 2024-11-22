package com.tweeter.demo.repository;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tweets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweets {
    @Id
    @Column(length = 100)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String content;
    private Date timeTweeted = Date.from(Instant.now());

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
