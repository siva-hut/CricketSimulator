package com.circketApplication.dao.repositories;

import com.circketApplication.dao.entities.GameDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<GameDao,Long> {
    List<GameDao> findByEndDateIsNull();
}
