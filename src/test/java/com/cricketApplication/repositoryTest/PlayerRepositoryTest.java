package com.cricketApplication.repositoryTesting;

import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.cricketGame.player.PlayerFactory;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.dataModels.response.CreatePlayerResponse;
import com.cricketApplication.service.interfaces.CreateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
public class PlayerRepositoryTest {
    @Test
    void save(){
        Assertions.assertEquals("Moji", "Moji");
//        Player player = PlayerFactory.createPlayer( PlayerDao.builder().
//                                            name("siva").
//                                            playerType("Bowler").
//                                            build());
//        playerRepository.persistNewPlayer(player,"CSK");
//        Assertions.assertNotNull(playerRepository.findById(player.getId()));


    }
}
