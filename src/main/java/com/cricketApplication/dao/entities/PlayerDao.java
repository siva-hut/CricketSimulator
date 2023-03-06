package com.cricketApplication.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Player")
public class PlayerDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(targetEntity = TeamDao.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TeamName", insertable = false, updatable = false)
    private TeamDao team;
    @Column(name = "TeamName")
    private String teamName;
    @Column(name = "PlayerName")
    private String name;
    private int ballsFaced;
    private int runsScored;
    private int runsGiven;
    private int ballsBowled;
    private int wicketsTaken;
    private String playerType;
}
