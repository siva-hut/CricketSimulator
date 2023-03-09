package com.cricketApplication.dao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Player")
@Document(indexName = "player")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDao {
    @Id
    @org.springframework.data.annotation.Id
    @Field(type = FieldType.Long, name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonBackReference
    @ManyToOne(targetEntity = TeamDao.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TeamName", insertable = false, updatable = false)
    @org.springframework.data.annotation.Transient
    @Field(type = FieldType.Nested)
    private TeamDao team;
    @Field(type = FieldType.Text)
    @Column(name = "TeamName")
    private String teamName;
    @Column(name = "PlayerName")
    @MultiField(mainField = @Field(type = FieldType.Text),otherFields = {@InnerField(suffix = "innerField", type = FieldType.Keyword)})
    private String name;
    private int ballsFaced;
    private int runsScored;
    private int runsGiven;
    private int ballsBowled;
    private int wicketsTaken;
    @Field(type = FieldType.Text)
    private String playerType;
}
