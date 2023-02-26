package com.circketApplication.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "TeamStats")
public class TeamStatsDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "TeamName")
    private String teamName;
    @Column(name ="GameId")
    private Long gameId ;
    private int score;
    private String battingOvers;
    private int wicketsTaken;
    private String bowlingovers;
    @ManyToOne(targetEntity=GameDao.class,fetch=FetchType.LAZY)
    @JoinColumn(name ="GameId",insertable=false, updatable=false)
    private GameDao game;
    @ManyToOne(targetEntity=TeamDao.class,fetch=FetchType.LAZY)
    @JoinColumn(name ="TeamName",insertable=false, updatable=false)
    private TeamDao team;


}
