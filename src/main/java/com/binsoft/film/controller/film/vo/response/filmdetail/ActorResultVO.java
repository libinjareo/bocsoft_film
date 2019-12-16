package com.binsoft.film.controller.film.vo.response.filmdetail;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActorResultVO implements Serializable {
    private String imgAddress;
    private String directorName;
    private String roleName;
}
