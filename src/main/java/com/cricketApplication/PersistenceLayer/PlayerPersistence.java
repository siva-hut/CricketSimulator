package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

@Component
public class PlayerPersistence {
    @Autowired
    @Lazy
    private PlayerRepository playerRepository;

    //Updating player, no Threads should do it at the same time, causes dirty reads
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void updatePlayer(Player player, String teamName) {
        PlayerDao playerDao = playerRepository.findById(player.getId()).get();
        playerDao.setTeamName(teamName);
        playerDao.setBallsBowled(playerDao.getBallsBowled() + player.getOversBowled().getNumberOfBalls());
        playerDao.setBallsFaced(playerDao.getBallsFaced() + player.getBallsFaced());
        playerDao.setRunsScored(playerDao.getRunsScored() + player.getRunsScored());
        playerDao.setRunsGiven(playerDao.getRunsGiven() + player.getRunsGiven());
        playerDao.setWicketsTaken(playerDao.getWicketsTaken() + player.getWicketsTaken());
        playerRepository.save(playerDao);
    }

    public void persistNewPlayer(Player player, String teamName) {
        System.out.println(player.getRunsGiven());
        PlayerDao playerDao = PlayerDao.builder().
                teamName(teamName).
                ballsBowled(player.oversBowled.getNumberOfBalls()).
                ballsFaced(player.getBallsFaced()).
                runsScored(player.getRunsScored()).
                runsGiven(player.getRunsGiven()).
                wicketsTaken(player.getWicketsTaken()).
                name(player.getPlayerName()).
                playerType(player.playerType()).build();
        playerRepository.save(playerDao);
        player.setId(playerDao.getId());
    }
}
