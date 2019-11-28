package com.binsoft.film.controller.common;

import com.binsoft.film.controller.exception.ParameterException;

/**
 * 基础VO
 */
public abstract class BaseVO {
    public abstract void checkParam() throws ParameterException;
}
