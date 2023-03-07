package com.cricketApplication.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Player")
@Document(indexName = "player")
public class PlayerDao {
    @Id
    @org.springframework.data.annotation.Id
    @Field(type = FieldType.Long, name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(targetEntity = TeamDao.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TeamName", insertable = false, updatable = false)
    private TeamDao team;
    @Column(name = "TeamName")
    private String teamName;
    @Column(name = "PlayerName")
    @Field(type = FieldType.Text, name = "name")
    private String name;
    private int ballsFaced;
    private int runsScored;
    private int runsGiven;
    private int ballsBowled;
    private int wicketsTaken;
    private String playerType;
}
