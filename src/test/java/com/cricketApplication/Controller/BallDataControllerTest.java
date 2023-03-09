package com.cricketApplication.Controller;

import com.cricketApplication.service.dataService.BallDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class BallDataControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BallDetailService ballDetailService;
    @Test
    void getEntireMatchDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getAllBallDetails?gameId=1").
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getOverDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getOverDetails?gameId=1&over=1").
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getSpecificBallDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getSpecificBallDetails?gameId=1&over=1").
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getBallDetailsWithinRange() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getBallDetailsWithinRange?gameId=1&startRange=1&endRange=2").
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
}