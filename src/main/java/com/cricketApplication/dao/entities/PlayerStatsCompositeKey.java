package com.circketApplication.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PlayerStatsCompositeKey {
    @Column(name="GameId")
    private Long gameId;
    @Column(name = "PlayerId")
    private Long playerId;

}
