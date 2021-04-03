package com.poe.ladderservice.domain.enums;

import lombok.Getter;

public enum LadderTypes {

    STANDARD("Standard"),
    HARDCORE("Hardcore"),
    SSF("SSF"),
    SSF_HARDCORE("SSF Hardcore");

    @Getter
    public final String type;

    private LadderTypes(String type) {
        this.type = type;
    }

}
