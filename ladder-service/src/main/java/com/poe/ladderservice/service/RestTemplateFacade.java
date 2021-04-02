package com.poe.ladderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poe.ladderservice.config.HttpEntityBuilder;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;

@Service
public class RestTemplateFacade {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpEntityBuilder httpEntityBuilder;

    public LadderDto getForLadderResponse(String uri) {
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntityBuilder.getConfiguredHttpEntity(), LadderDto.class).getBody();
    }
}
