package com.cricketApplication.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BallDataDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private int innings;
    private float overs;
    private Long gameId;
    @ManyToOne(targetEntity = PlayerDao.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "bowlerId", insertable = false, updatable = false)
    private PlayerDao bowler;
    @Column(name = "bowlerId")
    private Long bowlerId;
    @ManyToOne(targetEntity = PlayerDao.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "batsmanId", insertable = false, updatable = false)
    private PlayerDao batsman;
    @Column(name = "batsmanId")
    private Long batsmanId;
    private char ballOutCome;
    private int teamScore;
}
