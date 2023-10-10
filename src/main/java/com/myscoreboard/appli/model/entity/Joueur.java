package com.myscoreboard.appli.model.entity;

import java.util.List;

import lombok.Data;

@Data
public class Joueur {
    private Integer id;

    private String email;

    private String nickname;

    private List<String> wins;

    private List<String> contests;
}