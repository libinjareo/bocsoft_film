package com.binsoft.film.controller.cinema.vo.condition;

import lombok.Data;

import java.io.Serializable;

@Data
public class HallTypeResVO implements Serializable {
    private String halltypeId;
    private String halltypeName;
    private boolean isActive;
}
