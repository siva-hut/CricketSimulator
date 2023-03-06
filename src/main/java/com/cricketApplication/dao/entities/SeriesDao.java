package com.circketApplication.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class SeriesDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="series")
    List<GameDao> seriesGames;
}
