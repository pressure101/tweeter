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
    @SequenceGenerator(name="id", initialValue=2, allocationSize=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private Long id;
    private String username;
    private String content;
    private Date timeTweeted = Date.from(Instant.now());
}
