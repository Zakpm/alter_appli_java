package com.myscoreboard.appli.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.myscoreboard.appli")
public class CustomProperties {
    private String apiURL;
}