package com.circketApplication.dao.repositories;

import com.circketApplication.dao.entities.TeamStatsCompositeKey;
import com.circketApplication.dao.entities.TeamStatsDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamStatsRepository extends JpaRepository<TeamStatsDao, Long> {
}
