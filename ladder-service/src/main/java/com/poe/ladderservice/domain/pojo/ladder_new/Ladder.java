
package com.poe.ladderservice.domain.pojo.ladder_new;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ladder {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private Double total;
    private List<Entry> entries;

}
