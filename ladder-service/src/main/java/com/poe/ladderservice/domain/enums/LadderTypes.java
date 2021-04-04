package com.poe.ladderservice.domain.enums;

import lombok.Getter;

public enum LadderTypes {

    DELVE("Top Delve Depths"),
    UBERLAB("Top UberLab Times"),
    RACETO100("Top Race to 100"),
    UNKNOWN("Unknown Leaderboard");

    @Getter
    public final String type;

    private LadderTypes(String type) {
        this.type = type;
    }

}
