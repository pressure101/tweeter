package com.tweeter.demo.web;

import com.tweeter.demo.dto.Tweet;
import com.tweeter.demo.service.TweetsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TweeterController.class)
class TweeterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TweetsService tweetsService;

    @Test
    void helloPressure() throws Exception {
        mockMvc.perform(get("/v1/hello-pressure")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("hey you")));
    }

    @Test
    void getTweets() throws Exception {
        when(tweetsService.getTweets()).thenReturn(List.of());
        mockMvc.perform(get("/v1")).andDo(print()).andExpect(status().isOk());
    }
}