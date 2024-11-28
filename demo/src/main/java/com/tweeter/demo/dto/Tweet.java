package com.tweeter.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Tweet {
    private Integer id;
    private String username;
    private String content;
    private Date timeTweeted;
}
