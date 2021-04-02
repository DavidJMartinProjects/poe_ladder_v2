package com.poe.ladderservice.domain.pojo.ladder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ladder {
    private int total;
    private Date cached_since;
    private List<Entry> entries;
}