package com.cricketApplication.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Team")
@Document(indexName = "team")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDao {
    @org.springframework.data.annotation.Id
    @Id
    @Column(name = "TeamName")
    private String name;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrew;
    @JsonManagedReference
    @OneToMany( fetch=FetchType.EAGER, mappedBy = "team")
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<PlayerDao> players;
}