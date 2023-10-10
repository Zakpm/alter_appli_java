package com.myscoreboard.appli.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myscoreboard.appli.model.entity.Joueur;
import com.myscoreboard.appli.model.service.JoueurService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j // ! on peut utiliser une variable nommée 'log' qui permet d'écrire des logs
public class JoueurController {
    @Autowired
    JoueurService joueurService;

    @GetMapping("/tous-les-joueurs")
    public String joueurs(Model model) {
        List<Joueur> listJoueurs = joueurService.getAllJoueur();
        model.addAttribute("joueurs", listJoueurs);
        return "joueur/liste";
    }

    /** Exo : ajouter une route pour le chemin "/joueur/{id}" qui affiche les informations d'un
     *      joueur. L'afficher se fera avec une vue nommée "fiche.html" dans le dossier "joueur".
     *      
     */
    @GetMapping("/joueur/{id}")
    public String joueur(@PathVariable ("id") Integer id, Model model) {
        Joueur joueur = joueurService.getOneJoueur(id);

        log.info("\n---------------------" + joueur + "\n");
        model.addAttribute("joueur", joueur);
        return "joueur/fiche";
    }

    
    @GetMapping("/joueur/ajouter")
    public String formAjouter(Model model) {
        Joueur j = new Joueur();
        /**
         *  La méthode addAttribute de la classe Model permet de 
         * passer des variables à la vue 
         * => dans le fichier html, grâce à Thymleaf, on pourra 
         *      utiliser les variables ajoutés avec 'addAttribute'
         */
        model.addAttribute("joueur", j);
        return "joueur/form";
    }

    @PostMapping("/joueur/ajouter")
    public ModelAndView ajouterJoueur(@ModelAttribute Joueur joueur) {
        joueurService.saveJoueur(joueur);
        return new ModelAndView("redirect:/tous-les-joueurs");
    }

    /** ********************SUPRESSIONS ******************** */

    @GetMapping("joueur/{id}/supprimer")
    public String confirmation(@PathVariable Integer id, Model model){
        Joueur j = joueurService.getOneJoueur(id);
        if (j != null ) {
            model.addAttribute("joueur", j);
            
        } else {
            model.addAttribute("message", "404 : ce joueur n'existe pas");
        }

        return "joueur/confirmation.html";
    }

    @PostMapping("joueur/{id}/supprimer")
    public ModelAndView sppression(@PathVariable Integer id) {
        joueurService.deleteJoueur(id);
        return new ModelAndView("redirect:/tous-les-joueurs ");
    }
}