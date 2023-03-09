package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.ElasticSearchConfiguration;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
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
class PlayerPersistenceTest {
    @Autowired
    private PlayerPersistence playerPersistence;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamPersistence teamPersistence;
    private PlayerDao buildPlayerDao(){
        teamPersistence.persist("CSK");
        return EntityBuilder.buildPlayerDao("siva","Bowler","CSK");
    }
    @Test
    void save() {
        Long playerId = playerPersistence.save(buildPlayerDao()).getId();
        Assertions.assertNotNull(playerRepository.findById(playerId));
    }
}