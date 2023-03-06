package com.cricketApplication.repositoryTest;

import com.cricketApplication.PersistenceLayer.PlayerPersistence;
import com.cricketApplication.PersistenceLayer.TeamPersistence;
import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.cricketGame.player.PlayerFactory;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerPersistence playerPersistence;
    @Autowired
    private TeamPersistence teamPersistence;
    private Player player;
    @Test
    void persistNewPlayer(){
        teamPersistence.persist("CSK");
         player = PlayerFactory.createPlayer( PlayerDao.builder().
                                            name("siva").
                                            playerType("Bowler").
                                            build());
        playerPersistence.persistNewPlayer(player,"CSK");
        Assertions.assertNotNull(playerRepository.findById(player.getId()));
        updatePlayer();

    }
    void updatePlayer(){
        player.setRunsScored(10);
        player.setRunsGiven(10);
        player.setBallsFaced(10);
        player.setWicketsTaken(2);
        playerPersistence.updatePlayer(player,"CSK");
        PlayerDao playerDao = playerRepository.findById(player.getId()).get();
        Assertions.assertEquals(playerDao.getRunsScored(),player.getRunsScored());
        Assertions.assertEquals(playerDao.getRunsGiven(),player.getRunsGiven());
        Assertions.assertEquals(playerDao.getBallsFaced(),player.getBallsFaced());
        Assertions.assertEquals(playerDao.getWicketsTaken(),player.getWicketsTaken());
    }
}

