package com.cricketApplication.service.impl;

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
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class CreateServiceImplTest {
    @Mock
    private CreateService createService;
    @InjectMocks
    private PlayerRepository playerRepository;
    @Test
    void createPlayer() {
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setPlayerName("siva");
        createPlayerRequest.setPlayerType("Bowler");
        createPlayerRequest.setTeamName("SSS");
        when(playerRepository.save(any(PlayerDao.class))).thenReturn(new PlayerDao());
        Assertions.assertNotNull(createService.createPlayer(createPlayerRequest));
    }

    @Test
    void createTeam() {
    }

    @Test
    void createGame() {
    }
}