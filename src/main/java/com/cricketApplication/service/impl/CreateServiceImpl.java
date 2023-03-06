package com.cricketApplication.service.impl;

import com.cricketApplication.cricketGame.GameBuilder;
import com.cricketApplication.cricketGame.util.RandomGenerator;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import com.cricketApplication.dao.repositories.TeamRepository;
import com.cricketApplication.dataModels.request.CreateGameRequest;
import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.dataModels.request.CreateTeamRequest;
import com.cricketApplication.dataModels.response.CreateGameResponse;
import com.cricketApplication.dataModels.response.CreatePlayerResponse;
import com.cricketApplication.service.interfaces.CreateService;
import com.cricketApplication.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateServiceImpl implements CreateService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameService gameService;

    @Override
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        if (createPlayerRequest.getPlayerType() == null)
            createPlayerRequest.setPlayerType("s");
        CreatePlayerResponse createPlayerResponse = CreatePlayerResponse.builder().build();
        if (!createPlayerRequest.getPlayerType().equals("Batsman") && !createPlayerRequest.getPlayerType().equals("Bowler")) {
            createPlayerRequest.setPlayerType(RandomGenerator.getRandomGenerator().getRandomPlayer());
        }
        PlayerDao playerDao = PlayerDao.builder().name(createPlayerRequest.getPlayerName()).
                teamName(createPlayerRequest.getTeamName()).
                playerType(createPlayerRequest.getPlayerType())
                .build();
        try {
            playerRepository.save(playerDao);
            createPlayerResponse.setStatus("success");
            createPlayerResponse.setMessage("Player created successfully");
            createPlayerResponse.setPlayerId(playerDao.getId());
        } catch (Exception ex) {
            createPlayerResponse.setStatus("error");
            createPlayerResponse.setMessage("Team does not exist");
        }
        return createPlayerResponse;
    }

    @Override
    public String createTeam(CreateTeamRequest createTeamRequest) {
        try {
            if (teamRepository.findByName(createTeamRequest.getTeamName()) != null)
                return "Team already Exists";
            teamRepository.persist(createTeamRequest.getTeamName());
            return "Team created successfully";
        } catch (Exception ex) {
            return "Some error occurred";
        }
    }

    @Override
    public CreateGameResponse createGame(CreateGameRequest createGameRequest) {

        try {
            GameBuilder gameBuilder = new GameBuilder();
            gameBuilder.setTeam1Name(createGameRequest.getFirstBattingTeamName());
            gameBuilder.setTeam2Name(createGameRequest.getFirstBowlingTeamName());
            gameBuilder.setTotalOvers(createGameRequest.getTotalOvers());
            if (createGameRequest.startDate != null && createGameRequest.startDate.after(new Date())) {
                return scheduleGame(gameBuilder, createGameRequest.startDate);
            } else {
                return startGame(gameBuilder);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return CreateGameResponse.builder().status("error").message("could not create game").build();
        }
    }

    private CreateGameResponse scheduleGame(GameBuilder gameBuilder, Date date) {
        Long id = gameService.scheduleGame(gameBuilder, date);
        return CreateGameResponse.builder().
                status("success").
                message("Game scheduled successfully").
                gameId(id)
                .build();
    }

    private CreateGameResponse startGame(GameBuilder gameBuilder) {
        Long id = gameService.createGame(gameBuilder);
        return CreateGameResponse.builder().
                status("success").
                message("Game created successfully").
                gameId(id)
                .build();
    }

}
