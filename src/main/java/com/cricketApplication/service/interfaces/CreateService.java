package com.cricketApplication.service.interfaces;

import com.cricketApplication.dataModels.request.CreateGameRequest;
import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.dataModels.request.CreateTeamRequest;
import com.cricketApplication.dataModels.response.create.CreateGameResponse;
import com.cricketApplication.dataModels.response.create.CreatePlayerResponse;

public interface CreateService {
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest);

    public String createTeam(CreateTeamRequest createTeamRequest);

    public CreateGameResponse createGame(CreateGameRequest createGameRequest);
}
