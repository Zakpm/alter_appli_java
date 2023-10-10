package com.myscoreboard.appli.model.entity;


import lombok.Data;

@Data
public class Jeu {
    private Integer id;

    private String title;

    private Integer minPlayers;
    
    private Integer maxPlayers;

}