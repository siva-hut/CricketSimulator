package com.circketApplication.dao.repositories;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.dao.entities.BallDataDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BallDataRepository extends JpaRepository<BallDataDao, Long> {
    List<BallDataDao>findByGameId(Long gameID);
    List<BallDataDao>findByGameIdOrderByInnings(Long gameID);
//    @Query(value = "SELECT * FROM ball_data_dao WHERE overs BETWEEN :startRange AND :endRange",
//    nativeQuery = true)
    List<BallDataDao> findByOversBetweenAndGameId(float startRange,float endRange,Long gameId);
    void persistBallData(Game game);
}
