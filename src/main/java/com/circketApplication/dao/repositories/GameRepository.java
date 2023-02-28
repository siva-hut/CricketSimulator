package com.circketApplication.dao.repositories;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.dao.entities.GameDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Repository
public interface GameRepository extends JpaRepository<GameDao,Long> {
    List<GameDao> findByEndDateIsNull();
    List<GameDao> findByEndDateIsNullAndStartDateLessThan(Timestamp startDate);
    List<GameDao> findByGameActive(Boolean gameActive);
    List<GameDao> findByEndDateIsNullAndStartDateLessThanAndGameActive(Timestamp startDate, Boolean gameActive);
    void persistGameCreation(Game game);
    void persistGameCreation(Game game, Date date);
    void persistGameOnCompletion(Game game);
}
