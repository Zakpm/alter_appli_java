package com.myscoreboard.appli.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myscoreboard.appli.model.entity.Joueur;
import com.myscoreboard.appli.model.repository.JoueurRepository;

import lombok.Data;

@Service
@Data
public class JoueurService {
    @Autowired
    JoueurRepository joueurRepository;

    public List<Joueur> getAllJoueur() {
        return joueurRepository.getJoueurs();
    }

    public Joueur getOneJoueur(Integer id) {
        return joueurRepository.getJoueur(id);
    }
    
    public Joueur saveJoueur(Joueur j) {
        j.setNickname(j.getNickname().toUpperCase() );
        return joueurRepository.createJoueur(j);
    }

    public Boolean deleteJoueur(Integer id) {
        return joueurRepository.deleteJoueur(id);
    }
    
}