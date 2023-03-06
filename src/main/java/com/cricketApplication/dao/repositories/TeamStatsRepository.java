package com.cricketApplication.dao.repositories;

import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.util.Overs;
import com.cricketApplication.dao.entities.TeamStatsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamStatsRepository extends JpaRepository<TeamStatsDao, Long> {

}
