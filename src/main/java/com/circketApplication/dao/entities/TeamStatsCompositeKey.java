package com.circketApplication.dao.entities;

import com.circketApplication.dao.entities.TeamDao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class TeamStatsCompositeKey{
    @Column(name = "TeamName")
    private String teamName;
    @Column(name ="GameId")
    private Long GameId ;
}