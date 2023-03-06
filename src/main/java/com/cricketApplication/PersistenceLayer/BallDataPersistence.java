package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.dao.entities.BallDataDao;
import com.cricketApplication.dao.repositories.BallDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BallDataPersistence {
    @Autowired
    @Lazy
    private BallDataRepository ballDataRepository;

    public void persistBallData(Game game) {
        ballDataRepository.save(BallDataDao.builder().gameId(game.getId()).innings(game.getInnings()).overs(game.getOvers().getOversInFloat()).
                ballOutCome(game.getRun()).
                bowlerId(game.getBowlingTeam().getBowler().getId()).
                batsmanId(game.getBattingTeam().getBatsmanOnStrike().getId()).
                teamScore(game.getBattingTeam().getScore())
                .build());
    }
}
