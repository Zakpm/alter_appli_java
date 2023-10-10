package com.myscoreboard.appli.model.repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.myscoreboard.appli.configuration.CustomProperties;
import com.myscoreboard.appli.model.entity.Jeu;

@Component
public class JeuRepository {
    private CustomProperties properties;
    private String baseUrlApi;

    public JeuRepository (CustomProperties customProperties) {
        this.properties = customProperties;
        this.baseUrlApi = properties.getApiURL();
    }

    public List<Jeu> getJeux() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Jeu>> response = restTemplate.exchange(
            baseUrlApi + "/games",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Jeu>>() {}
            );
            return response.getBody();
    }
}