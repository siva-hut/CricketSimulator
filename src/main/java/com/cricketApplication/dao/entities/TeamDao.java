package com.cricketApplication.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Team")
public class TeamDao {
    @Id
    @Column(name = "TeamName")
    private String name;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrew;
    @JsonIgnore
    @OneToMany( fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "team")
    private List<PlayerDao> players;
}