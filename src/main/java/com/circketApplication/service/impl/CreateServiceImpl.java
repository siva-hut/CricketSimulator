package com.circketApplication.service.impl;

import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.cricketGame.player.PlayerFactory;
import com.circketApplication.cricketGame.util.RandomGenerator;
import com.circketApplication.dao.entities.TeamDao;
import com.circketApplication.dao.repositories.TeamRepository;
import com.circketApplication.dataModels.request.CreateGameRequest;
import com.circketApplication.dataModels.request.CreatePlayerRequest;
import com.circketApplication.dataModels.request.CreateTeamRequest;
import com.circketApplication.dataModels.response.CreateGameResponse;
import com.circketApplication.dataModels.response.CreatePlayerResponse;
import com.circketApplication.service.interfaces.CreateService;
import com.circketApplication.service.interfaces.CricketGamePersistence;
import com.circketApplication.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateServiceImpl implements CreateService {
    @Autowired
    private CricketGamePersistence cricketGamePersistence;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GameService gameService;

    @Override
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        CreatePlayerResponse createPlayerResponse = CreatePlayerResponse.builder().build();
        Player player;
        if (!createPlayerRequest.getPlayerType().equals("Batsman") && !createPlayerRequest.getPlayerType().equals("Bowler")) {
            createPlayerRequest.setPlayerType(RandomGenerator.getRandomGenerator().getRandomPlayer());
        }
        if (createPlayerRequest.getPlayerType().equals("Batsman")) {
            player = PlayerFactory.getBatsman(createPlayerRequest.getPlayerName());
        } else {
            player = PlayerFactory.getBowler(createPlayerRequest.getPlayerName());
        }
        try {
            cricketGamePersistence.persistNewPlayer(player, createPlayerRequest.getTeamName());
            createPlayerResponse.setStatus("success");
            createPlayerResponse.setMessage("Player created successfully");
            createPlayerResponse.setPlayerId(player.getId());
        } catch (Exception ex) {
            createPlayerResponse.setStatus("error");
            createPlayerResponse.setMessage("Team does not exist");
        }
        return createPlayerResponse;
    }

    @Override
    public String createTeam(CreateTeamRequest createTeamRequest) {
        try {
            if(teamRepository.findByName(createTeamRequest.getTeamName())!=null)
                return "Team already Exists";
            cricketGamePersistence.persist(createTeamRequest.getTeamName());
            return "Team created successfully";
        } catch (Exception ex) {

        }
        return "Some error occurred";
    }

    @Override
    public CreateGameResponse createGame(CreateGameRequest createGameRequest) {

        try {
            GameBuilder gameBuilder = new GameBuilder();
            gameBuilder.setTeam1Name(createGameRequest.getFirstBattingTeamName());
            gameBuilder.setTeam2Name(createGameRequest.getFirstBowlingTeamName());
            gameBuilder.setTotalOvers(createGameRequest.getTotalOvers());
            if (createGameRequest.startDate!=null && createGameRequest.startDate.after(new Date())) {
                System.out.println(createGameRequest.startDate);
                return scheduleGame(gameBuilder,createGameRequest.startDate);
            } else {
                return startGame(gameBuilder);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CreateGameResponse.builder().status("error").message("could not create game").build();
    }

    private CreateGameResponse scheduleGame(GameBuilder gameBuilder,Date date) {
        Long id = gameService.scheduleGame(gameBuilder,date);
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
