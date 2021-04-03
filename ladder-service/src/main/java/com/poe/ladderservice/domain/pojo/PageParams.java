package com.poe.ladderservice.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageParams {

    private int offset;
    private int limit;
    private String league;

}
