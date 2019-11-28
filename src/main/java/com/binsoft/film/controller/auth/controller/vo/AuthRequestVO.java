package com.binsoft.film.controller.auth.controller.vo;

import com.binsoft.film.controller.common.BaseVO;
import com.binsoft.film.controller.exception.ParameterException;
import lombok.Data;

@Data
public class AuthRequestVO extends BaseVO {
    private String userName;
    private String password;

    @Override
    public void checkParam() throws ParameterException {

    }
}
