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

