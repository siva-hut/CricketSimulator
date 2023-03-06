package com.cricketApplication.serviceTest;

import com.cricketApplication.cricketGame.GameBuilder;
import com.cricketApplication.dao.repositories.GameRepository;
import com.cricketApplication.service.interfaces.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameServiceTest {
    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository gameRepository;

    @Test
    void createGame(){
        GameBuilder gameBuilder = new GameBuilder();
        Long id = gameService.createGame(gameBuilder);
        Assertions.assertNotNull(gameRepository.findById(id));
    }

}
