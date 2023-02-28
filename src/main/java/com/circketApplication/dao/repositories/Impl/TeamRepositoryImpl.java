package com.circketApplication.dao.repositories.Impl;

import com.circketApplication.cricketGame.Team;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.dao.entities.TeamDao;
import com.circketApplication.dao.repositories.PlayerRepository;
import com.circketApplication.dao.repositories.TeamRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

@Component
public class TeamRepositoryImpl {
    @Autowired
    @Lazy
    private TeamRepository teamRepository;
    @Autowired
    @Lazy
    private PlayerRepository playerRepository;
    public void persistAndLoadPlayers(Team team) {
        if(teamRepository.findByName(team.getTeamName())==null)
        {
            TeamDao teamDao = TeamDao.builder()
                    .name(team.getTeamName())
                    .gamesLost(0)
                    .gamesWon(0)
                    .gamesDrew(0).build();
            teamRepository.save(teamDao);
        }
        team.createTeamPlayers(teamRepository.findByName(team.getTeamName()).getPlayers());
        persistPlayersInTeam(team);
    }
    private void persistPlayersInTeam(Team team)
    {
        for (Player player: team.getPlayers()) {
            if(player.getId()==null)
                playerRepository.persistNewPlayer(player, team.getTeamName());
        }
    }
    public void persist(String teamName) {
        teamRepository.save(TeamDao.builder().name(teamName).build());
    }
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void updateTeam(Team team1,Team team2)
    {
        TeamDao teamDao1 = teamRepository.findByName(team1.getTeamName());
        TeamDao teamDao2 = teamRepository.findByName(team2.getTeamName());
        if(team1.getScore()>team2.getScore()) {
            teamDao1.setGamesWon(teamDao1.getGamesWon()+1);
            teamDao2.setGamesLost(teamDao2.getGamesLost()+1);
        }
        else if(team1.getScore()<team2.getScore()){
            teamDao1.setGamesLost(teamDao1.getGamesLost()+1);
            teamDao2.setGamesWon(teamDao2.getGamesWon()+1);
        }
        else{
            teamDao1.setGamesDrew(teamDao1.getGamesDrew()+1);
            teamDao2.setGamesDrew(teamDao2.getGamesDrew()+1);
        }
        teamRepository.save(teamDao1);
        teamRepository.save(teamDao2);
    }
}
