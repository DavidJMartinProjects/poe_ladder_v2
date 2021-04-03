package com.poe.ladderservice.scheduled.facade;

import com.poe.ladderservice.scheduled.facade.config.HttpEntityBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class RestTemplateFacade {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntityBuilder httpEntityBuilder;

    public ResponseEntity<String> getForString(String url) {
        ResponseEntity<String> response;
        try {
            log.info("making GET request to url: {}", url);
            response =  restTemplate.exchange(url, HttpMethod.GET, httpEntityBuilder.getConfiguredHttpEntity(), String.class);
            log.info("response status code was: {}", response.getStatusCode());
            return response;
        } catch (HttpStatusCodeException ex) {
            log.info("encountered http error during GET request to url: {} {}", url, ex.getStatusCode());
            response = new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
        } catch (Exception ex) {
            log.info("encountered error during GET request to url: {} {}", url, ex.getMessage());
            response = new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return response;
    }

}