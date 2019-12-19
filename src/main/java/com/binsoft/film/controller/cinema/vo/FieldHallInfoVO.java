package com.binsoft.film.controller.cinema.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 某一场次的影厅信息
 */
@Data
public class FieldHallInfoVO implements Serializable {
    private String hallFieldId;
    private String hallName;
    private String price;
    private String seatFile;
    private String soldSeats;
}

