package com.poe.ladderservice.domain.pojo.league;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeagueDto {
    private String id;
    private String realm;
    private String url;
    private Date startAt;
    private Date endAt;
    private String description;
    private Date registerAt;
    private boolean delveEvent;
    private List<Rule> rules;
}

