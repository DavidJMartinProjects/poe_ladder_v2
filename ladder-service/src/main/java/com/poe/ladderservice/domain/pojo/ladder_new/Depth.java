
package com.poe.ladderservice.domain.pojo.ladder_new;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Depth {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private Integer solo;
    public Integer getSolo() {
        return solo;
    }
    public void setSolo(Integer solo) {
        this.solo = solo;
    }

}
