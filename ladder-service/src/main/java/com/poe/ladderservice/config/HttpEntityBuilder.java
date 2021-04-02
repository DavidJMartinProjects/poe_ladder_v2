package com.poe.ladderservice.config;


import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class HttpEntityBuilder {

    @Autowired
    private HttpEntityPropertiesConfig httpRequestConfig;

    private HttpHeaders httpHeaders = new HttpHeaders();

    @PostConstruct
    public void init() {
        setRequestHeaders();
    }

    public HttpEntity<String> getConfiguredHttpEntity() {
        return new HttpEntity<String>("parameters", httpHeaders);
    }

    private void setRequestHeaders() {
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpRequestConfig.getHeaders().forEach((key, value) -> {
            httpHeaders.add(key,  value);
        });
        httpRequestConfig.getCookies().forEach((value) -> {
            httpHeaders.add("Cookie", value);
        });
    }

}