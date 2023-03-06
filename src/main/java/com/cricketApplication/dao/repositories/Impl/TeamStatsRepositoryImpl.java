package com.cricketApplication.dao.repositories.Impl;

import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.util.Overs;
import com.cricketApplication.dao.entities.TeamStatsDao;
import com.cricketApplication.dao.repositories.TeamStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TeamStatsRepositoryImpl {
    @Autowired
    @Lazy
    private TeamStatsRepository teamStatsRepository;

    public void updateTeamStats(Team team, Overs bowlingOvers, Long gameId) {
        teamStatsRepository.save(TeamStatsDao.builder().teamName(team.getTeamName())
                .gameId(gameId)
                .score(team.getScore())
                .battingOvers(team.getBattingOvers().getOvers()).
                wicketsTaken(team.getWicketsLost()).
                bowlingovers(bowlingOvers.getOvers()).
                build());
    }
}
