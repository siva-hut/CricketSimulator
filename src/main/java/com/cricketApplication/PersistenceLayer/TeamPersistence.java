package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.cricketGame.util.RandomGenerator;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.entities.TeamDao;
import com.cricketApplication.dao.repositories.TeamRepository;
import com.github.javafaker.Faker;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TeamPersistence {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerPersistence playerPersistence;

    public void persistAndLoadPlayers(Team team) {
        if (teamRepository.findByName(team.getTeamName()) == null) {
            TeamDao teamDao = TeamDao.builder()
                    .name(team.getTeamName())
                    .gamesLost(0)
                    .gamesWon(0)
                    .gamesDrew(0).build();
            teamRepository.save(teamDao);
        }
        loadPlayers(teamRepository.findByName(team.getTeamName()).getPlayers(), team);
        persistPlayersInTeam(team);
    }

    //Loads players from DB or creates if not enough players
    private void loadPlayers(List<PlayerDao> playerDaos, Team team) {
        Faker faker = new Faker();
        if (playerDaos == null)
            playerDaos = new ArrayList<>();
        while (playerDaos.size() < 11) {
            playerDaos.add(PlayerDao.builder().
                    name(faker.name().name()).
                    teamName(team.getTeamName()).
                    playerType(RandomGenerator.getRandomGenerator().getRandomPlayer()).build());
        }
        team.createTeamPlayers(playerDaos);
    }

    private void persistPlayersInTeam(Team team) {
        for (Player player : team.getPlayers()) {
            if (player.getId() == null)
                playerPersistence.persistNewPlayer(player, team.getTeamName());
        }
    }

    public void persist(String teamName) {
        teamRepository.save(TeamDao.builder().name(teamName).build());
    }

    //Updating Team wins and losses
    //Lock type - pessimistic To prevent Dirty reads
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void updateTeam(Team team1, Team team2) {
        TeamDao teamDao1 = teamRepository.findByName(team1.getTeamName());
        TeamDao teamDao2 = teamRepository.findByName(team2.getTeamName());
        if (team1.getScore() > team2.getScore()) {
            teamDao1.setGamesWon(teamDao1.getGamesWon() + 1);
            teamDao2.setGamesLost(teamDao2.getGamesLost() + 1);
        } else if (team1.getScore() < team2.getScore()) {
            teamDao1.setGamesLost(teamDao1.getGamesLost() + 1);
            teamDao2.setGamesWon(teamDao2.getGamesWon() + 1);
        } else {
            teamDao1.setGamesDrew(teamDao1.getGamesDrew() + 1);
            teamDao2.setGamesDrew(teamDao2.getGamesDrew() + 1);
        }
        teamRepository.save(teamDao1);
        teamRepository.save(teamDao2);
    }
}
