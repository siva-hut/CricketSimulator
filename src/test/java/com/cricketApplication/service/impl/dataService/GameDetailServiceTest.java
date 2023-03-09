package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.repositories.GameRepository;
import com.cricketApplication.service.dataService.GameDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
class GameDetailServiceTest {
    @InjectMocks
    private GameDetailService gameDetailService;
    @Mock
    private GameRepository gameRepository;
    @Test
    void getAllGames() {
        when(gameRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(gameDetailService.getAllGames());
    }

    @Test
    void getAllActiveGames() {
        when(gameRepository.findByGameActive(anyBoolean())).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(gameDetailService.getAllActiveGames());
    }

}