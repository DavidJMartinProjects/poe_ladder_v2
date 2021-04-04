package com.poe.ladderservice.scheduled.facade.httpEntity;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "http.request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpEntityConfig {
    private Map<String, String> headers;
    private List<String> cookies;
}