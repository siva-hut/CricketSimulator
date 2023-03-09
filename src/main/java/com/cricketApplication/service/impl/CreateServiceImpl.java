package com.cricketApplication.service.impl;

import com.cricketApplication.PersistenceLayer.PlayerPersistence;
import com.cricketApplication.PersistenceLayer.TeamPersistence;
import com.cricketApplication.cricketGame.GameBuilder;
import com.cricketApplication.cricketGame.util.RandomGenerator;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.TeamRepository;
import com.cricketApplication.dataModels.request.CreateGameRequest;
import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.dataModels.request.CreateTeamRequest;
import com.cricketApplication.dataModels.response.create.CreateGameResponse;
import com.cricketApplication.dataModels.response.create.CreatePlayerResponse;
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
    private TeamPersistence teamPersistence;
    @Autowired
    private PlayerPersistence playerPersistence;
    @Autowired
    private GameService gameService;

    private boolean isNotValidPlayerType(CreatePlayerRequest createPlayerRequest) {
        if (createPlayerRequest.getPlayerType() == null) {
            return true;
        }
        boolean condition1 = !createPlayerRequest.getPlayerType().equals("Batsman");
        boolean condition2 = !createPlayerRequest.getPlayerType().equals("Bowler");
        return condition1 && condition2;
    }
    @Override
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {

        if (isNotValidPlayerType(createPlayerRequest)) {
            String randomPlayerType = RandomGenerator.getRandomPlayerType();
            createPlayerRequest.setPlayerType(randomPlayerType);
        }
        String playerName = createPlayerRequest.getPlayerName();
        String playerType = createPlayerRequest.getPlayerType();
        String teamName = createPlayerRequest.getTeamName();
        PlayerDao playerDao = EntityBuilder.buildPlayerDao(playerName,playerType,teamName);
        playerPersistence.save(playerDao);
        return CreatePlayerResponse.createPlayerResponse(playerDao.getId());
    }

    @Override
    public String createTeam(CreateTeamRequest createTeamRequest) {
        if (teamRepository.findByName(createTeamRequest.getTeamName()) != null)
            return "Team already Exists";
        teamPersistence.persist(createTeamRequest.getTeamName());
        return "Team created successfully";
    }
    private GameBuilder buildGame(CreateGameRequest createGameRequest){
        String team1Name = createGameRequest.getFirstBattingTeamName();
        String team2Name = createGameRequest.getFirstBowlingTeamName();
        int totalOvers = createGameRequest.getTotalOvers();
        return new GameBuilder(team1Name,team2Name,totalOvers);
    }
    private boolean startDateIsInFuture(Date startDate){
        if(startDate == null)
            return false;
        return startDate.after(new Date());

    }
    @Override
    public CreateGameResponse createGame(CreateGameRequest createGameRequest) {
        GameBuilder gameBuilder = buildGame(createGameRequest);
        if(startDateIsInFuture(createGameRequest.startDate)){
            return scheduleGame(gameBuilder,createGameRequest.startDate);
        }
        return startGame(gameBuilder);
    }

    private CreateGameResponse scheduleGame(GameBuilder gameBuilder, Date date) {
        Long gameId = gameService.scheduleGame(gameBuilder, date);
        String SCHEDULED_GAME = "Game scheduled successfully";
        return CreateGameResponse.createGameResponse(SCHEDULED_GAME,gameId);
    }

    private CreateGameResponse startGame(GameBuilder gameBuilder) {
        Long gameId = gameService.createGame(gameBuilder);
        String CREATED_GAME = "Game Created successfully";
        return CreateGameResponse.createGameResponse(CREATED_GAME,gameId);
    }

}
