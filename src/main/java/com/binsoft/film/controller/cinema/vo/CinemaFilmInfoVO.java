package com.binsoft.film.controller.cinema.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaFilmInfoVO implements Serializable{
    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmType;
    private String filmCats;
    private String actors;
    private String imgAddress;
}
