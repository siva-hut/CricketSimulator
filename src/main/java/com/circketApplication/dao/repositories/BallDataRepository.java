package com.circketApplication.dao.repositories;

import com.circketApplication.dao.entities.BallDataDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BallDataRepository extends JpaRepository<BallDataDao, Long> {
    List<BallDataDao>findByGameId(Long gameID);
    List<BallDataDao>findByGameIdOrderByInnings(Long gameID);
//    @Query(value = "SELECT * FROM ball_data_dao WHERE overs BETWEEN :startRange AND :endRange",
//    nativeQuery = true)
    List<BallDataDao> findByOversBetweenAndGameId(float startRange,float endRange,Long gameId);
}
