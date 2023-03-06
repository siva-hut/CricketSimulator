package com.cricketApplication.repositoryTest;

import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.repositories.PlayerRepository;
import com.cricketApplication.dao.repositories.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TeamRepositoryTest {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Test
    void persistAndLoadPlayers(){
        Team team = new Team();
        team.setTeamName("CSK");
        teamRepository.persistAndLoadPlayers(team);
        for (Player player:team.getPlayers()) {
            Assertions.assertNotNull(playerRepository.findById(player.getId()));
        }
    }
}
