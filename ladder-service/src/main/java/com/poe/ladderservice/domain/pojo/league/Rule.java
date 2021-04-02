package com.poe.ladderservice.domain.pojo.league;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule {
    private String id;
    private String name;
    private String description;
}