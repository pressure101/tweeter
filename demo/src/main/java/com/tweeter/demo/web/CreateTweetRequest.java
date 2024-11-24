package com.tweeter.demo.web;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateTweetRequest {
    private String username;
    private String content;
}
