package com.binsoft.film.controller.exception;

import lombok.Data;

@Data
public class FilmException extends Exception {
    private Integer code;
    private String errMsg;

    public FilmException(Integer code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }
}
