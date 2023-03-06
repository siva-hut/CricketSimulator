package com.circketApplication.dao.repositories;

import com.circketApplication.cricketGame.Team;
import com.circketApplication.dao.entities.TeamDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamDao,Long> {

    TeamDao findByName(String teamName);

    @Query(value = "SELECT team_Name From Team", nativeQuery = true)
    public List<String> findAllTeamName();
    void persistAndLoadPlayers(Team team);
    void persist(String teamName);
    void updateTeam(Team team1,Team team2);
}
