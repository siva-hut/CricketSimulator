package com.cricketApplication.PersistenceLayer;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.dao.EntityBuilder;
import com.cricketApplication.dao.elasticSearchRepository.ElasticBallDataRepository;
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
    @Autowired
    @Lazy
    private ElasticBallDataRepository elasticBallDataRepository;

    public BallDataDao persistBallData(Game game) {
        BallDataDao ballDataDao = EntityBuilder.buildBallDataDao(game);
        return persistBallData(ballDataDao);
    }
    private BallDataDao persistBallData(BallDataDao ballDataDao){
         ballDataRepository.save(ballDataDao);
         elasticBallDataRepository.save(ballDataDao);
         return ballDataDao;
    }
}
