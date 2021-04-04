package com.poe.ladderservice.scheduled.facade.httpEntity;


import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class HttpEntityBuilder {

    private HttpEntity<String> httpEntity;

    @Autowired
    private HttpEntityConfig httpRequestConfig;

    @PostConstruct
    public void init() {
        httpEntity = new HttpEntity<>("parameters", getRequestHeaders());
    }

    public HttpEntity<String> getConfiguredHttpEntity() {
        return httpEntity;
    }

    private HttpHeaders getRequestHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpRequestConfig.getHeaders().forEach(httpHeaders::add);
        httpRequestConfig.getCookies().forEach(value -> httpHeaders.add("Cookie", value));
        return httpHeaders;
    }

}