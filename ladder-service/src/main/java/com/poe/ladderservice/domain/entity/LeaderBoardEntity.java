package com.poe.ladderservice.domain.entity;

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
    private long id;

    private String characterId;
    private String rankDifference;
    private String account;
    private String character;
    private String ascendancy;
    private String time;
    private String timeDifference;
    private String timeFormatted;
    private String depth;
    private String depthDifference;
    private String experienceDifference;
    private String progress;
    private String leaderboard;
    private String timeStamp;
    private String realm;

    private boolean online;
    private boolean dead;

    private long experience;

    private int rank;
    private int level;
}