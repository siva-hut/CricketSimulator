package com.circketApplication.dao.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Game") public class GameDb {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany
    @JoinColumn(name = "teamId")
    private TeamDb battingTeam;
    @ManyToMany
    @JoinColumn(name = "teamId")
    private TeamDb bowlingTeam;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
