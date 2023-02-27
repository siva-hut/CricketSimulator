package com.circketApplication.dao.entities;

import com.circketApplication.dao.repositories.TeamStatsRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@ToString
@Table(name = "Game")
@NoArgsConstructor
@AllArgsConstructor
public class GameDao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JoinColumn(name="firstBattingTeamName", insertable=false, updatable=false)
    @ManyToOne(targetEntity=TeamDao.class,fetch=FetchType.LAZY)
    private TeamDao firstBattingTeam;
    @Column(name="firstBattingTeamName")
    private String firstBattingTeamName;
    @JoinColumn(name="firstBowlingTeamName", insertable=false, updatable=false)
    @ManyToOne(targetEntity=TeamDao.class,fetch=FetchType.LAZY)
    private TeamDao firstBowlingTeam;
    @Column(name="firstBowlingTeamName")
    private String firstBowlingTeamName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SeriesId")
    private SeriesDao series;
    private Timestamp startDate;
    private Timestamp endDate;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="game")
    private List<TeamStatsDao> teamStatsDaos;
    private int totalOvers;
    private boolean gameActive;

}
