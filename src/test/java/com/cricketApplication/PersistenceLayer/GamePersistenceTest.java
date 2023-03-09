package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.ElasticSearchConfiguration;
import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.cricketGame.GameBuilder;
import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dao.repositories.GameRepository;
import com.cricketApplication.dao.repositories.PlayerRepository;
import com.cricketApplication.dao.repositories.TeamRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(ElasticSearchConfiguration.class)
@ComponentScan(basePackages = "com.cricketApplication.PersistenceLayer")
class GamePersistenceTest {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePersistence gamePersistence;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    private Game buildGame(){
        GameBuilder gameBuilder = new GameBuilder("CSK","SRK",5);
        return gameBuilder.getGame();
    }
    @Test
    void save() {
        GameDao gameDao = EntityBuilder.buildGameDao(buildGame(),new Date(),false);
        gamePersistence.save(gameDao);
        Assertions.assertNotNull(gameRepository.findById(gameDao.getId()));
    }

    @Test
    void persistGameCreation() {
        Game game = buildGame();
        gamePersistence.persistGameCreation(game);
        Assertions.assertNotNull(gameRepository.findById(game.getId()));
        assertTeamNotNull(game.getBattingTeam());
        assertTeamNotNull(game.getBowlingTeam());
    }
    private void assertTeamNotNull(Team team){
        Assertions.assertNotNull(teamRepository.findByName(team.getTeamName()));
        for(Player player:team.getPlayers()){
            Assertions.assertNotNull(player);
            Assertions.assertNotNull(playerRepository.findById(player.getId()));
        }
    }
    @Test
    void persistGameOnCompletion() {
        Game game = buildGame();
        gamePersistence.persistGameCreation(game);
        while(!game.isGameOver())
            game.simulateNextBall();
        gamePersistence.persistGameOnCompletion(game);
        Assertions.assertNotNull(gameRepository.findById(game.getId()).get().getEndDate());
    }
}