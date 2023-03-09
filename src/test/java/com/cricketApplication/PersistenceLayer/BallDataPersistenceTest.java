package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.ElasticSearchConfiguration;
import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.cricketGame.GameBuilder;
import com.cricketApplication.dao.entities.BallDataDao;
import com.cricketApplication.dao.repositories.BallDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(ElasticSearchConfiguration.class)
@ComponentScan(basePackages = "com.cricketApplication.PersistenceLayer")
class BallDataPersistenceTest {
    @Autowired
    private BallDataPersistence ballDataPersistence;
    @Autowired
    private GamePersistence gamePersistence;
    @Autowired
    private BallDataRepository ballDataRepository;

    private Game buildGame(){
        GameBuilder gameBuilder = new GameBuilder("CSK","SRK",10);
        return gameBuilder.getGame();
    }
    @Test
    void persistBallData() {
        Game game = buildGame();
        gamePersistence.persistGameCreation(game);
        game.simulateNextBall();
        BallDataDao ballDataDao = ballDataPersistence.persistBallData(game);
        Assertions.assertNotNull(ballDataRepository.findById(ballDataDao.getId()));
    }
}