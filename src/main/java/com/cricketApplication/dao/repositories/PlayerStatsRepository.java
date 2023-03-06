package com.cricketApplication.dao.repositories;

import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.entities.PlayerStatsCompositeKey;
import com.cricketApplication.dao.entities.PlayerStatsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStatsDao, PlayerStatsCompositeKey> {

}
