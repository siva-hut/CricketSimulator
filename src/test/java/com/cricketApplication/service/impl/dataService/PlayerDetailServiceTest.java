package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import com.cricketApplication.service.dataService.PlayerDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PlayerDetailServiceTest {
    @InjectMocks
    private PlayerDetailService playerDetailService;
    @Mock
    private PlayerRepository playerRepository;
    @Test
    void getAllPlayers() {
        when(playerRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(playerDetailService.getAllPlayers());
    }

    @Test
    void getPlayer() {
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(new PlayerDao()));
        Assertions.assertNotNull(playerDetailService.getPlayer(1l));
    }
}