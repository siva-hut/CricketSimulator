package com.circketApplication.dao.repositories;

import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.dao.entities.PlayerStatsCompositeKey;
import com.circketApplication.dao.entities.PlayerStatsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStatsDao, PlayerStatsCompositeKey> {
    void updatePlayerStats(Player player, Long gameId);
}
