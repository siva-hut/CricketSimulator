package com.circketApplication.dao.repositories;

import com.circketApplication.dao.entities.GamePlayerDetails;
import com.circketApplication.dao.entities.PlayerDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamePlayerDetailsRepository extends JpaRepository<GamePlayerDetails,Long> {
    List<GamePlayerDetails> findByGameIdAndTeamNameOrderByIdAsc(Long gameId, String teamName);
}
