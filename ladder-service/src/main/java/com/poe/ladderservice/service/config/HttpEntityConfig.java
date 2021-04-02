package com.poe.ladderservice.service.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "http.request")
public class HttpEntityConfig {

    private Map<String, String> headers;
    private List<String> cookies;

    public List<String> getCookies() {
        return cookies;
    }
    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

}