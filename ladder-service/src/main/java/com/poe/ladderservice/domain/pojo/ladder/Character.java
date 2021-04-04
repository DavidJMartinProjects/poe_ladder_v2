package com.poe.ladderservice.domain.pojo.ladder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    private String name;
    private int level;
    @JsonProperty("id")
    private String characterId;
    @JsonProperty("class")
    private String charClass;

    private long experience;
    private Depth depth;

}
