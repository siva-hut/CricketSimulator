package com.cricketApplication.service.impl;

import com.cricketApplication.PersistenceLayer.TeamPersistence;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.service.interfaces.CreateService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
class CreateServiceImplTest {
    @Autowired
    private CreateService createService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamPersistence teamPersistence;
    @Test
    void createPlayer() {
        teamPersistence.persist("CSK");
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setPlayerName("siva");
        createPlayerRequest.setPlayerType("Bowler");
        createPlayerRequest.setTeamName("CSK");
        Assertions.assertNotNull(createService.createPlayer(createPlayerRequest));
    }

    @Test
    void createTeam() {
    }

    @Test
    void createGame() {
    }
}