package com.poe.ladderservice.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaderBoardEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String characterId;

    @Column(name="rank")
    private int rank;

    @Column(name="rank_difference")
    private String rankDifference;

    @Column(name="account")
    private String account;

    private String realm;

    @Column(name="online")
    private boolean online;

    @Column(name="dead")
    private boolean dead;

    @Column(name="characterName")
    private String character;

    @Column(name="ascendancy")
    private String ascendancy;

    @Column(name="time")
    private String time;

    @Column(name="time_difference")
    private String timeDifference;

    @Column(name="time_formatted")
    private String timeFormatted;

    @Column(name="depth")
    private String depth;

    @Column(name="depth_difference")
    private String depthDifference;

    @Column(name="level")
    private int level;

    @Column(name="xp")
    private long experience;

    @Column(name="xp_difference")
    private long experienceDifference;

    @Column(name="progress")
    private String progress;

    @Column(name="league")
    private String league;

    @Column(name="leaderboard")
    private String leaderboard;

    @Column(name="timeStamp")
    private String timeStamp;

}
