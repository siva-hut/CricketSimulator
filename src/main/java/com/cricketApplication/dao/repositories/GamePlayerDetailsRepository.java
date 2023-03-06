package com.circketApplication.dao.repositories;

import com.circketApplication.cricketGame.Team;
import com.circketApplication.dao.entities.GamePlayerDetails;
import com.circketApplication.dao.entities.PlayerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GamePlayerDetailsRepository extends JpaRepository<GamePlayerDetails,Long> {
    List<GamePlayerDetails> findByGameIdAndTeamNameOrderByIdAsc(Long gameId, String teamName);
    void setMatchPlayerDetails(Team team, Long gameId);
}
