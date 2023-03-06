package com.cricketApplication.dao.repositories;

import com.cricketApplication.cricketGame.Game;
import com.cricketApplication.dao.entities.GameDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameDao, Long> {
    List<GameDao> findByEndDateIsNull();

    List<GameDao> findByEndDateIsNullAndStartDateLessThan(Timestamp startDate);

    List<GameDao> findByGameActive(Boolean gameActive);

    List<GameDao> findByEndDateIsNullAndStartDateLessThanAndGameActive(Timestamp startDate, Boolean gameActive);

}
