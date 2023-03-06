package com.cricketApplication.dao.repositories;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.dao.entities.BallDataDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallDataRepository extends JpaRepository<BallDataDao, Long> {
    List<BallDataDao> findByGameId(Long gameID);

    List<BallDataDao> findByGameIdOrderByInnings(Long gameID);

    //    @Query(value = "SELECT * FROM ball_data_dao WHERE overs BETWEEN :startRange AND :endRange",
//    nativeQuery = true)
    List<BallDataDao> findByOversBetweenAndGameId(float startRange, float endRange, Long gameId);
}
