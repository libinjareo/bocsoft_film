package com.binsoft.film.controller.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 影院详情实体
 */
@Data
public class CinemaDetailVO implements Serializable {
    private String cinemaId;
    private String imgUrl;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaPhone;

}
