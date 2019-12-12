package com.binsoft.film.controller.cinema.vo.request;

import com.binsoft.film.controller.common.BaseVO;
import com.binsoft.film.controller.exception.ParameterException;
import lombok.Data;

import java.io.Serializable;

@Data
public class FieldInfoRequestVO extends BaseVO implements Serializable {
    @Override
    public void checkParam() throws ParameterException {

    }

    private String cinemaId;
    private String fieldId;


}
