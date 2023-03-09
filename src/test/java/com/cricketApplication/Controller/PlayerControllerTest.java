package com.cricketApplication.Controller;

import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dataModels.response.getAll.GetAllPlayerResponse;
import com.cricketApplication.dataModels.response.get.GetPlayerResponse;
import com.cricketApplication.service.dataService.PlayerDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerDetailService playerDetailService;
    @Test
    void getAllPlayers() throws Exception {
        GetAllPlayerResponse getAllPlayerResponse = GetAllPlayerResponse.getAllPlayerResponse(new ArrayList<>());
        when(playerDetailService.getAllPlayers()).thenReturn(getAllPlayerResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getAllPlayers").
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getPlayerDetails() throws Exception {
        GetPlayerResponse getPlayerResponse = GetPlayerResponse.getPlayerResponse(new PlayerDao());
        when(playerDetailService.getPlayer(anyLong())).thenReturn(getPlayerResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getPlayer?playerId=1").
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
}