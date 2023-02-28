package com.circketApplication.dao.repositories.Impl;

import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.dao.entities.PlayerStatsCompositeKey;
import com.circketApplication.dao.entities.PlayerStatsDao;
import com.circketApplication.dao.repositories.PlayerStatsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PlayerStatsRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    @Lazy
    private PlayerStatsRepository playerStatsRepository;
    public void updatePlayerStats(Player player, Long gameId) {
        PlayerStatsCompositeKey playerStatsCompositeKey = new PlayerStatsCompositeKey();
        playerStatsCompositeKey.setPlayerId(player.getId());
        playerStatsCompositeKey.setGameId(gameId);
        PlayerStatsDao playerStatsDao = new PlayerStatsDao();
        playerStatsDao.setPlayerStatsCompositeKey(playerStatsCompositeKey);
        playerStatsDao.setBallsBowled(player.getOversBowled().getNumberOfBalls());
        playerStatsDao.setBallsFaced(player.getBallsFaced());
        playerStatsDao.setWicketsTaken(player.getWicketsTaken());
        playerStatsDao.setRunsScored(player.getRunsScored());
        playerStatsDao.setRunsGiven(player.getRunsGiven());
        playerStatsRepository.save(playerStatsDao);
    }
}
