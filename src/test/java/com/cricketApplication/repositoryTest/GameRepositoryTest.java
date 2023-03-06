package com.cricketApplication.repositoryTest;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.cricketGame.GameBuilder;
import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dao.entities.TeamDao;
import com.cricketApplication.dao.repositories.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;

@DataJpaTest
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;

    @Test
    void persistGameCreation(){
        Team  team1 = new Team();
        team1.setTeamName("CSK");
        Team  team2 = new Team();
        team2.setTeamName("SSK");
        GameBuilder gameBuilder = new GameBuilder();
        Game game = gameBuilder.getGame();
        gameRepository.persistGameCreation(game);
        Assertions.assertNotNull(gameRepository.findById(game.getId()));
    }
}
