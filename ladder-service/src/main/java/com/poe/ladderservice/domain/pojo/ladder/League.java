package com.poe.ladderservice.domain.pojo.ladder;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {
    private String id;
    private String realm;
    private String url;
    private Date startAt;
    private Object endAt;
    private String description;
    private Date registerAt;
    private boolean delveEvent;
    private List<Object> rules;
}