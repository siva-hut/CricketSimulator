package com.circketApplication.Controller;

import com.circketApplication.dataModels.request.CreateGameRequest;
import com.circketApplication.dataModels.request.CreatePlayerRequest;
import com.circketApplication.dataModels.request.CreateTeamRequest;
import com.circketApplication.dataModels.response.CreateGameResponse;
import com.circketApplication.dataModels.response.CreatePlayerResponse;
import com.circketApplication.service.impl.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CreateController {
    @Autowired
    CreateService createService;
    @PostMapping("/createPlayer")
    public CreatePlayerResponse createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest) {
        return createService.createPlayer(createPlayerRequest);
    }
    @PostMapping("/createTeam")
    public String createTeam(@RequestBody CreateTeamRequest createTeamRequest) {
        return createService.createTeam(createTeamRequest);
    }
    @PostMapping("/createGame")
    public CreateGameResponse createGame(@RequestBody CreateGameRequest createGameRequest){
        return createService.createGame(createGameRequest);
    }
}
