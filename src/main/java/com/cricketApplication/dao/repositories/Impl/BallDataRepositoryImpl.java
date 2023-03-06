package com.cricketApplication.dao.repositories.Impl;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.dao.entities.BallDataDao;
import com.cricketApplication.dao.repositories.BallDataRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component // Must be @Component !!
public class BallDataRepositoryImpl { // must have the exact repo name + Impl !!

    @PersistenceContext
    private EntityManager entityManager;

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