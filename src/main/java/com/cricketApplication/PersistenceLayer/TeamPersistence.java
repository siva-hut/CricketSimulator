package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.cricketGame.player.PlayerFactory;
import com.cricketApplication.cricketGame.util.RandomGenerator;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.elasticSearchRepository.ElasticSearchTeamRepository;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.entities.TeamDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
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
    @Autowired
    private ElasticSearchTeamRepository elasticSearchTeamRepository;

    public void persistAndLoadPlayers(Team team) {
        if (teamRepository.findByName(team.getTeamName()) == null) {
            persist(team.getTeamName());
        }
        loadPlayers(teamRepository.findByName(team.getTeamName()).getPlayers(), team);
    }
    //Loads players from DB or creates if not enough players
    private void loadPlayers(List<PlayerDao> playerDaos, Team team) {
        if (playerDaos == null)
        {
            playerDaos = new ArrayList<>();
        }

        while (playerDaos.size() < 11) {
            String playerName = RandomGenerator.getRandomName();
            String playerType = RandomGenerator.getRandomPlayerType();
            PlayerDao playerDao = EntityBuilder.buildPlayerDao(playerName,playerType,team.getTeamName());
            playerPersistence.save(playerDao);
            playerDaos.add(playerDao);
        }

        List<Player> playerList = PlayerFactory.createPlayers(playerDaos);
        team.createTeamPlayers(playerList);
    }

    public void persist(String teamName) {
        TeamDao teamDao = EntityBuilder.buildTeamDao(teamName);
        elasticSave(teamRepository.save(teamDao));
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
    private void elasticSave(TeamDao teamDao){
        elasticSearchTeamRepository.save(teamDao);
    }
    public void save(String teamName){
        elasticSearchTeamRepository.save(teamRepository.findByName(teamName));
    }
}
