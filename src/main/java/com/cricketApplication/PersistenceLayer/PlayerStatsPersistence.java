package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.entities.PlayerStatsCompositeKey;
import com.cricketApplication.dao.entities.PlayerStatsDao;
import com.cricketApplication.dao.repositories.PlayerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PlayerStatsPersistence {
    @Autowired
    @Lazy
    private PlayerStatsRepository playerStatsRepository;

    public void updatePlayerStats(Player player, Long gameId) {
        PlayerStatsDao playerStatsDao = EntityBuilder.buildPlayerStatsDao(player,gameId);
        playerStatsRepository.save(playerStatsDao);
    }

}
