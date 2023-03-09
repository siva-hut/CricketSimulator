package com.cricketApplication.Controller.serviceControllers;
import com.cricketApplication.dataModels.request.CreateGameRequest;
import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.dataModels.request.CreateTeamRequest;
import com.cricketApplication.dataModels.response.create.CreateGameResponse;
import com.cricketApplication.dataModels.response.create.CreatePlayerResponse;
import com.cricketApplication.service.interfaces.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CreateServiceController {
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
