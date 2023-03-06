package com.cricketApplication.repositoryTest;

import com.cricketApplication.PersistenceLayer.GamePersistence;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePersistence gamePersistence;
    @Test
    void persistGameCreation(){
        Team  team1 = new Team();
        team1.setTeamName("CSK");
        Team  team2 = new Team();
        team2.setTeamName("SSK");
        GameBuilder gameBuilder = new GameBuilder();
        Game game = gameBuilder.getGame();
        gamePersistence.persistGameCreation(game);
        Assertions.assertNotNull(gameRepository.findById(game.getId()));
    }
}
