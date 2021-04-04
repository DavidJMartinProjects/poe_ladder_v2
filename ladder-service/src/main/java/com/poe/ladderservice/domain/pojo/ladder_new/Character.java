package com.poe.ladderservice.domain.pojo.ladder_new;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    private String name;
    private Integer level;
    @JsonProperty("id")
    private String characterId;
    @JsonProperty("class")
    private String charClass;

    private Double experience;
    private Depth depth;

}
