package com.poe.ladderservice.domain.pojo.league;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LeagueDto {

    public String id;
    public String realm;
    public String url;
    public Date startAt;
    public Object endAt;
    public String description;
    public Date registerAt;
    public boolean delveEvent;
    public List<Rule> rules;

}

