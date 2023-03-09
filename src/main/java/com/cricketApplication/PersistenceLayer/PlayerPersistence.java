package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.elasticSearchRepository.ElasticPlayerRepository;
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
    @Autowired
    @Lazy
    private ElasticPlayerRepository elasticPlayerRepository;

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
        save(playerDao);
    }
    public PlayerDao save(PlayerDao playerDao){
        playerRepository.save(playerDao);
        elasticPlayerRepository.save(playerDao);
        return playerDao;
    }
}
