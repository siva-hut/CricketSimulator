package com.cricketApplication.Controller;

import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.dataModels.request.CreateTeamRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

@SpringBootTest
@AutoConfigureMockMvc
class CreateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    String postBody(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
    @Order(2)
    @Test
    void createPlayer() throws Exception {
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setTeamName("CSK");
        createPlayerRequest.setPlayerName("siva");
        createPlayerRequest.setPlayerType("Bowler");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/createPlayer")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(postBody(createPlayerRequest)).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Order(1)
    @Test
    void createTeam() throws Exception {

        CreateTeamRequest createTeamRequest = new CreateTeamRequest();
        createTeamRequest.setTeamName("CSK");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/createTeam")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(postBody(createTeamRequest)).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
}