package com.poe.ladderservice.scheduled.urls;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "urls.leaderboards")
public class UrlsConfig {
    String delve;
    String raceTo100;
    String uberLab;
}
