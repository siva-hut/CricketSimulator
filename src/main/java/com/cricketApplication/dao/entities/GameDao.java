package com.cricketApplication.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.ValueConverter;
import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@ToString
@Table(name = "Game")
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "game")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDao {
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @JoinColumn(name = "firstBattingTeamName", insertable = false, updatable = false)
    @ManyToOne(targetEntity = TeamDao.class, fetch = FetchType.LAZY)
    private TeamDao firstBattingTeam;
    @Column(name = "firstBattingTeamName")
    private String firstBattingTeamName;
    @JoinColumn(name = "firstBowlingTeamName", insertable = false, updatable = false)
    @ManyToOne(targetEntity = TeamDao.class, fetch = FetchType.LAZY)
    private TeamDao firstBowlingTeam;
    @Column(name = "firstBowlingTeamName")
    private String firstBowlingTeamName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SeriesId")
    private SeriesDao series;
    @ValueConverter(DateConverter.class)
    private Timestamp startDate;
    @ValueConverter(DateConverter.class)
    private Timestamp endDate;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
    private List<TeamStatsDao> teamStatsDaos;
    private int totalOvers;
    private boolean gameActive;

}

class DateConverter implements PropertyValueConverter {

    public static final String PREFIX = "foo-";

    @Override
    public Object write(Object value) {
        return value.toString();
    }
    @Override
    public Object read(Object value) {

        String valueString = value.toString();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        // you can change format of date
        Date date = null;
        try {
            date = formatter.parse(valueString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}