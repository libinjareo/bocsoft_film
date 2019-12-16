package com.binsoft.film.controller.film.vo.request;

import com.binsoft.film.controller.common.BaseVO;
import com.binsoft.film.controller.exception.ParameterException;
import lombok.Data;

import java.io.Serializable;

@Data
public class DescribeFilmListReqVO extends BaseVO implements Serializable {
    @Override
    public void checkParam() throws ParameterException {

    }

    private String showType = "1";//电影类型
    private String sortId = "1";
    private String catId = "99";
    private String sourceId = "99";
    private String yearId = "99";
    private String nowPage = "1";
    private String pageSize = "18";
}
