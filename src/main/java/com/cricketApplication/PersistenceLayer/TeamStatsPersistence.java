package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.util.Overs;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.entities.TeamStatsDao;
import com.cricketApplication.dao.repositories.TeamStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TeamStatsPersistence {
    @Autowired
    @Lazy
    private TeamStatsRepository teamStatsRepository;

    public void updateTeamStats(Team team, Overs bowlingOvers, Long gameId) {
        String overs = bowlingOvers.getOvers();
        TeamStatsDao teamStatsDao = EntityBuilder.buildTeamStatsDao(team,gameId,overs);
        teamStatsRepository.save(teamStatsDao);
    }
}
