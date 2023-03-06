package com.cricketApplication.Controller;

import com.cricketApplication.dataModels.request.CreateGameRequest;
import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.dataModels.request.CreateTeamRequest;
import com.cricketApplication.dataModels.response.CreateGameResponse;
import com.cricketApplication.dataModels.response.CreatePlayerResponse;
import com.cricketApplication.service.interfaces.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreateController {
    @Autowired
    private CreateService createService;

    @PostMapping("/createPlayer")
    public CreatePlayerResponse createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest) {
        return createService.createPlayer(createPlayerRequest);
    }

    @PostMapping("/createTeam")
    public String createTeam(@RequestBody CreateTeamRequest createTeamRequest) {
        return createService.createTeam(createTeamRequest);
    }

    @PostMapping("/createGame")
    public CreateGameResponse createGame(@RequestBody CreateGameRequest createGameRequest) {
        return createService.createGame(createGameRequest);
    }
}
