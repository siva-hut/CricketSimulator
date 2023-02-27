package com.circketApplication.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GamePlayerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long playerId;
    @ManyToOne(targetEntity=PlayerDao.class)
    @JoinColumn(name = "playerId", insertable=false, updatable=false)
    private PlayerDao playerDao;
    private Long gameId;
    @ManyToOne(targetEntity=GameDao.class,fetch=FetchType.LAZY)
    @JoinColumn(name = "gameId", insertable=false, updatable=false)
    private GameDao gameDao;
    private String teamName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
