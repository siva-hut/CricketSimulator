package com.cricketApplication.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playerstats")
public class PlayerStatsDao {
    @EmbeddedId
    private PlayerStatsCompositeKey playerStatsCompositeKey;
    @ManyToOne(targetEntity = GameDao.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "GameId", insertable = false, updatable = false)
    private GameDao game;
    @ManyToOne(targetEntity = PlayerDao.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PlayerId", insertable = false, updatable = false)
    private PlayerDao player;
    private int ballsFaced;
    private int runsScored;
    private int runsGiven;
    private int ballsBowled;
    private int wicketsTaken;
}

