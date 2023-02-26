package com.circketApplication.dao.repositories;

import com.circketApplication.dao.entities.TeamDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamDao,Long> {

    TeamDao findByName(String teamName);
}
