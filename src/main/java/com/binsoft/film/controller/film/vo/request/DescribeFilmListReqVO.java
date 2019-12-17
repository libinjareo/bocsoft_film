package com.binsoft.film.controller.film.vo.request;

import com.binsoft.film.controller.common.BaseVO;
import com.binsoft.film.controller.exception.ParameterException;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DescribeFilmListReqVO extends BaseVO implements Serializable {
    @Override
    public void checkParam() throws ParameterException {

        List typeList = Lists.newArrayList();
        typeList.add("1");
        typeList.add("2");
        typeList.add("3");

        if(!typeList.contains(getShowType())){
            throw new ParameterException(400,"无效的电影类型");
        }

        List sortList = Lists.newArrayList();

        sortList.add("1");
        sortList.add("2");
        sortList.add("3");

        if(!sortList.contains(getSortId())){
            throw new ParameterException(400,"无效的排序类型");
        }
    }

    private String showType = "1";//电影类型
    private String sortId = "1";
    private String catId = "99";
    private String sourceId = "99";
    private String yearId = "99";
    private String nowPage = "1";
    private String pageSize = "18";
}
