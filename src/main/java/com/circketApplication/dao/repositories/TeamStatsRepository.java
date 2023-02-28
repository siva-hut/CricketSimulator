package com.circketApplication.dao.repositories;

import com.circketApplication.cricketGame.Team;
import com.circketApplication.cricketGame.util.Overs;
import com.circketApplication.dao.entities.TeamStatsCompositeKey;
import com.circketApplication.dao.entities.TeamStatsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamStatsRepository extends JpaRepository<TeamStatsDao, Long> {
    void updateTeamStats(Team team, Overs bowlingOvers, Long gameId);
}
