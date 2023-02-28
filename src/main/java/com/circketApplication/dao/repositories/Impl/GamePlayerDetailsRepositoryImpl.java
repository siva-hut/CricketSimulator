package com.circketApplication.dao.repositories.Impl;

import com.circketApplication.cricketGame.Team;
import com.circketApplication.cricketGame.player.Player;
import com.circketApplication.dao.entities.GamePlayerDetails;
import com.circketApplication.dao.repositories.GamePlayerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

@Component
public class GamePlayerDetailsRepositoryImpl {
    @Autowired
    @Lazy
    private GamePlayerDetailsRepository gamePlayerDetailsRepository;
    public void setMatchPlayerDetails(Team team, Long gameId) {
        for (Player player: team.getPlayers()){
            gamePlayerDetailsRepository.save(GamePlayerDetails.builder().
                    playerId(player.getId()).
                    gameId(gameId).
                    teamName(team.getTeamName()).build());
        }
    }
}
