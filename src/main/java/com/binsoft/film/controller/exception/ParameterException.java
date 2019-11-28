package com.binsoft.film.controller.exception;

import lombok.Data;

/**
 * VO参数 验证异常
 */
@Data
public class ParameterException extends Exception {

    private Integer code;
    private String errMsg;

    public ParameterException(Integer code, String errMsg) {
        super(errMsg);
        this.code = code;
        this.errMsg = errMsg;
    }

}
