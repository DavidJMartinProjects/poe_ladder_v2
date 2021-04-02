package com.poe.ladderservice.domain.pojo.ladder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String name;
    private String realm;
    private Challenges challenges;
}
