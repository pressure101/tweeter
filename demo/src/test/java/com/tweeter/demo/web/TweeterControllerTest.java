package com.tweeter.demo.web;

import com.tweeter.demo.dto.Tweet;
import com.tweeter.demo.service.TweetsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("numberOfCalls")
    void loadTestHelloPressure(int numberOfCalls) throws Exception{
        var startTime = System.currentTimeMillis();

        for(int i = 0; i < numberOfCalls; i++) {
            mockMvc.perform(get("/v1/hello-pressure")).andExpect(status().isOk())
                    .andExpect(content().string(containsString("hey you")));
        }

        var runtime = System.currentTimeMillis() - startTime;

        System.out.println("Number of calls " + numberOfCalls + " took " + runtime);
    }

    static private Stream<Arguments> numberOfCalls() {
        return Stream.of(Arguments.of(100),
                Arguments.of(1000),
                Arguments.of(10000),
                Arguments.of(100000)
        );
    }


}