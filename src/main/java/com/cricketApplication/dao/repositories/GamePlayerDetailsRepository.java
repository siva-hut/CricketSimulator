package com.cricketApplication.dao.repositories;

import com.cricketApplication.cricketGame.Team;
import com.cricketApplication.dao.entities.GamePlayerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamePlayerDetailsRepository extends JpaRepository<GamePlayerDetails, Long> {
    List<GamePlayerDetails> findByGameIdAndTeamNameOrderByIdAsc(Long gameId, String teamName);

}
