package com.circketApplication.dao.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Player")
public class PlayerDb {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public TeamDb getTeam() {
        return team;
    }

    public PlayerDb() {
    }
    public PlayerDb(String name, TeamDb team)
    {
        this.name = name;
        this.team = team;
    }
    public PlayerDb(String name)
    {
        this.name = name;
    }
    public void setTeam(TeamDb team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "teamId")
    private TeamDb team;
    private String name;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
