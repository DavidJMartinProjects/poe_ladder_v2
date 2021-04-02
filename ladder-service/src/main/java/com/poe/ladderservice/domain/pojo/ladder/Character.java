package com.poe.ladderservice.domain.pojo.ladder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character{
    public String id;
    public String name;
    public int level;
    public String characterClass;
    public int score;
    public long experience;
}