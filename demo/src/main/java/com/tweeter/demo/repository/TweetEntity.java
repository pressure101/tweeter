package com.tweeter.demo.repository;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tweets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String content;
    private Date timeTweeted = Date.from(Instant.now());
}
