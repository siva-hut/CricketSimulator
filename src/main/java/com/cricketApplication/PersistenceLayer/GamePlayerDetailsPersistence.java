package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.entities.GamePlayerDetails;
import com.cricketApplication.dao.repositories.GamePlayerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GamePlayerDetailsPersistence {
    @Autowired
    @Lazy
    private GamePlayerDetailsRepository gamePlayerDetailsRepository;

    public void setMatchPlayerDetails(Team team, Long gameId) {
        for (Player player : team.getPlayers()) {
            gamePlayerDetailsRepository.save(
                    EntityBuilder.buildGamePlayerDetails(player.getId(), gameId, team.getTeamName())
            );
        }
    }
}
