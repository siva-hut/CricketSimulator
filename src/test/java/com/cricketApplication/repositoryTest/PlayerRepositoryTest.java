package com.cricketApplication.repositoryTest;

import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.cricketGame.player.PlayerFactory;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository playerRepository;
    private Player player;
    @Test
    void persistNewPlayer(){

         player = PlayerFactory.createPlayer( PlayerDao.builder().
                                            name("siva").
                                            playerType("Bowler").
                                            build());
        playerRepository.persistNewPlayer(player,"CSK");
        Assertions.assertNotNull(playerRepository.findById(player.getId()));
        updatePlayer();

    }
    void updatePlayer(){
        player.setRunsScored(10);
        player.setRunsGiven(10);
        player.setBallsFaced(10);
        player.setWicketsTaken(2);
        playerRepository.updatePlayer(player,"CSK");
        PlayerDao playerDao = playerRepository.findById(player.getId()).get();
        Assertions.assertEquals(playerDao.getRunsScored(),player.getRunsScored());
        Assertions.assertEquals(playerDao.getRunsGiven(),player.getRunsGiven());
        Assertions.assertEquals(playerDao.getBallsFaced(),player.getBallsFaced());
        Assertions.assertEquals(playerDao.getWicketsTaken(),player.getWicketsTaken());
    }
}

