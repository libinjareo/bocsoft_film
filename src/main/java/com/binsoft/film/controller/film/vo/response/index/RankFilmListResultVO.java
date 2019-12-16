package com.binsoft.film.controller.film.vo.response.index;

import lombok.Data;

import java.io.Serializable;

@Data
public class RankFilmListResultVO implements Serializable {
    private String filmId;
    private String imgAddress;
    private String filmName;

    private String boxNum;
    private String expectNum;
    private String score;
}
