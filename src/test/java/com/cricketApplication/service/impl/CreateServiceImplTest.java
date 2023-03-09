package com.cricketApplication.service.impl;

import com.cricketApplication.PersistenceLayer.PlayerPersistence;
import com.cricketApplication.PersistenceLayer.TeamPersistence;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.TeamRepository;
import com.cricketApplication.dataModels.request.CreatePlayerRequest;
import com.cricketApplication.dataModels.response.create.CreatePlayerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CreateServiceImplTest {
    @InjectMocks
    private CreateServiceImpl createService;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private TeamPersistence teamPersistence;
    @Mock
    private PlayerPersistence playerPersistence;

    private PlayerDao buildPlayerDao(){
        PlayerDao playerDao = EntityBuilder.buildPlayerDao("siva","Bowler","CSK");
        playerDao.setId(1L);
        return playerDao;
    }
    @Test
    void createPlayer() {
        System.out.println("siva");
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setPlayerName("siva");
        createPlayerRequest.setPlayerType("Bowler");
        createPlayerRequest.setTeamName("CSK");
        when(playerPersistence.save(any(PlayerDao.class))).thenReturn(buildPlayerDao());
        Assertions.assertInstanceOf(CreatePlayerResponse.class,createService.createPlayer(createPlayerRequest));
    }
}