package com.circketApplication.service.interfaces;

import com.circketApplication.dataModels.request.CreateGameRequest;
import com.circketApplication.dataModels.request.CreatePlayerRequest;
import com.circketApplication.dataModels.request.CreateTeamRequest;
import com.circketApplication.dataModels.response.CreateGameResponse;
import com.circketApplication.dataModels.response.CreatePlayerResponse;

public interface CreateService {
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest);
    public String createTeam(CreateTeamRequest createTeamRequest);
    public CreateGameResponse createGame(CreateGameRequest createGameRequest);
}
