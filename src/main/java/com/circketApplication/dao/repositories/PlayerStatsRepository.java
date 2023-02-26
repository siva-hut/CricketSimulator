package com.circketApplication.dao.repositories;

import com.circketApplication.dao.entities.PlayerStatsCompositeKey;
import com.circketApplication.dao.entities.PlayerStatsDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerStatsRepository extends JpaRepository<PlayerStatsDao, PlayerStatsCompositeKey> {
}
