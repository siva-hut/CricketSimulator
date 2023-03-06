package com.circketApplication.dao.repositories.Impl;

import com.circketApplication.cricketGame.Team;
import com.circketApplication.cricketGame.util.Overs;
import com.circketApplication.dao.entities.TeamStatsDao;
import com.circketApplication.dao.repositories.TeamStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TeamStatsRepositoryImpl {
    @Autowired
    @Lazy
    private TeamStatsRepository teamStatsRepository;
    public void updateTeamStats(Team team, Overs bowlingOvers, Long gameId){
        teamStatsRepository.save(TeamStatsDao.builder().teamName(team.getTeamName())
                .gameId(gameId)
                .score(team.getScore())
                .battingOvers(team.getBattingOvers().getOvers()).
                wicketsTaken(team.getWicketsLost()).
                bowlingovers(bowlingOvers.getOvers()).
                build());
    }
}
