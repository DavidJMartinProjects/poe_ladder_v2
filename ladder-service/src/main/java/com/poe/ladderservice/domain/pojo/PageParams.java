package com.poe.ladderservice.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageParams {

    private String league;
    private int offset;
    private int limit;

    public PageParams(String offset, String limit) {
        this.offset = Integer.parseInt(offset);
        this.limit = Integer.parseInt(limit);;
    }

}
