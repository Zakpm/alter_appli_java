package com.myscoreboard.appli.model.repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.myscoreboard.appli.configuration.CustomProperties;
import com.myscoreboard.appli.model.entity.Joueur;

@Component
public class JoueurRepository {
   private CustomProperties properties;
   private  String baseUrlApi;

    public JoueurRepository(CustomProperties customProperties) {
        this.properties = customProperties;
        this.baseUrlApi = properties.getApiURL();
    }

    public List<Joueur> getJoueurs() {
        /** 
         * L'objet de la classe RestTemplate fait des requêtes HTTP et convertit
         * le JSON qui est retourné dans la réponse HTTP en objet JAVA.
         */
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Joueur>> response = restTemplate.exchange(
            baseUrlApi + "/players",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Joueur>>() {}
            );

            return response.getBody();
    }

    public Joueur getJoueur(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Joueur> response = restTemplate.exchange(baseUrlApi + "/player/" + id,
         HttpMethod.GET, 
         null, 
         Joueur.class
         );
         return response.getBody();
    }

    public Joueur  createJoueur(Joueur j) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Joueur> requestBody = new HttpEntity<Joueur>(j);
        ResponseEntity<Joueur> response = restTemplate.exchange(
            baseUrlApi + "/player" ,
            HttpMethod.POST,
            requestBody,
            Joueur.class
            );
            return response.getBody();
    }

    public Boolean deleteJoueur(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(
            baseUrlApi + "/player/" + id,
            HttpMethod.DELETE,
        null, 
        Boolean.class
        );
        return response.getBody();

    }



}