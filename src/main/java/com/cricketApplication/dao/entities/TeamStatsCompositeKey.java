package com.cricketApplication.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class TeamStatsCompositeKey {
    @Column(name = "TeamName")
    private String teamName;
    @Column(name = "GameId")
    private Long GameId;
}