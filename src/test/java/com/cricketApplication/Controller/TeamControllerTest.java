package com.cricketApplication.Controller;

import com.cricketApplication.dao.entities.TeamDao;
import com.cricketApplication.dataModels.response.getAll.GetAllTeamResponse;
import com.cricketApplication.dataModels.response.get.GetTeamDetailResponse;
import com.cricketApplication.service.dataService.TeamDetailService;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeamDetailService teamDetailService;
    @Test
    void getAllTeams() throws Exception {
        GetAllTeamResponse getAllTeamResponse = GetAllTeamResponse.getAllTeamResponse(new ArrayList<>());
        when(teamDetailService.getAllTeams()).thenReturn(getAllTeamResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getAllTeams").
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getTeamDetails() throws Exception {
        GetTeamDetailResponse getTeamDetailResponse = GetTeamDetailResponse.getTeamDetailResponse(new TeamDao());
        when(teamDetailService.getTeamDetails(anyString())).thenReturn(getTeamDetailResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/getTeamDetails?teamName=csk").
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
}