package com.tweeter.demo.DataConfig;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tweets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweets {
    @Id
    @Column(length = 100)
    private String username;
    private String content;
    private Date timeTweeted;
}
