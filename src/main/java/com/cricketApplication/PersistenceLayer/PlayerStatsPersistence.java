package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.player.Player;
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
        PlayerStatsDao playerStatsDao = new PlayerStatsDao();
        playerStatsDao.setPlayerStatsCompositeKey(getPlayerStatsCompositeKey(player, gameId));
        playerStatsDao.setBallsBowled(player.getOversBowled().getNumberOfBalls());
        playerStatsDao.setBallsFaced(player.getBallsFaced());
        playerStatsDao.setWicketsTaken(player.getWicketsTaken());
        playerStatsDao.setRunsScored(player.getRunsScored());
        playerStatsDao.setRunsGiven(player.getRunsGiven());
        playerStatsRepository.save(playerStatsDao);
    }

    private PlayerStatsCompositeKey getPlayerStatsCompositeKey(Player player, Long gameId) {
        PlayerStatsCompositeKey playerStatsCompositeKey = new PlayerStatsCompositeKey();
        playerStatsCompositeKey.setPlayerId(player.getId());
        playerStatsCompositeKey.setGameId(gameId);
        return playerStatsCompositeKey;
    }
}
