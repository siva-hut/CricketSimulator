package com.cricketApplication.dao.repositories;

import com.cricketApplication.cricketGame.player.Player;
import com.cricketApplication.dao.entities.PlayerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerDao, Long> {

}
