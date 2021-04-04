
package com.poe.ladderservice.domain.pojo.ladder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private Integer rank;
    private Boolean dead;
    private Boolean online;
    private Character character;
    private Account account;
    private String time;

}
