package com.poe.ladderservice.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaderBoardEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String characterId;
    private int rank;
    private String rankDifference;
    private String account;
    private boolean online;
    private boolean dead;
    private String character;
    private String ascendancy;
    private String time;
    private String timeDifference;
    private String timeFormatted;
    private String depth;
    private String depthDifference;
    private int level;
    private long experience;
    private String experienceDifference;
    private String progress;
    private String leaderboard;
    private String timeStamp;
    private String realm;

}