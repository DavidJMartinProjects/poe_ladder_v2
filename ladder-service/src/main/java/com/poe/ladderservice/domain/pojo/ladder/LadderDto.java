package com.poe.ladderservice.domain.pojo.ladder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LadderDto {
    private League league;
    private Ladder ladder;
}