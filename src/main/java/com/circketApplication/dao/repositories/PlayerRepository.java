package com.circketApplication.dao.repositories;

import com.circketApplication.dao.entities.PlayerDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerDao,Long> {
}
