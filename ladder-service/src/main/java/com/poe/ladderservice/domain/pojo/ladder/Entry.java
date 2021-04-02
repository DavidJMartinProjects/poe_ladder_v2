package com.poe.ladderservice.domain.pojo.ladder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    private int rank;
    private boolean dead;
    private boolean online;
    private Character character;
    private Account account;
}

