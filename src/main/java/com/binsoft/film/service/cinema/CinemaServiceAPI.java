package com.binsoft.film.service.cinema;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.controller.cinema.vo.*;
import com.binsoft.film.controller.cinema.vo.condition.AreaResVO;
import com.binsoft.film.controller.cinema.vo.condition.BrandResVO;
import com.binsoft.film.controller.cinema.vo.condition.HallTypeResVO;
import com.binsoft.film.controller.cinema.vo.request.DescribeCinemaRequestVO;

import java.util.List;

public interface CinemaServiceAPI {

    /**
     * 根据条件查询影院列表
     * @param describeCinemaRequestVO
     * @return
     */
    Page<CinemaVO> describeCinemaInfo(DescribeCinemaRequestVO describeCinemaRequestVO);

    //----------------------------以下为获取查询条件----------------
    List<BrandResVO> describeBrandConditions(int brandId);
    List<AreaResVO>  describeAreaConditions(int areaId);
    List<HallTypeResVO> describeHallTypeConditions(int hallTypeId);

    /**
     * 根据影院编号获取影院信息
     * @param cinemaId
     * @return
     */
    CinemaDetailVO describeCinemaDetails(String cinemaId);

    /**
     * 根据影院编号获取场次信息
     * @param cinemaId
     * @return
     */
    Page<CinemaFilmVO> describeFieldsAndFilmInfo(String cinemaId);

    /**
     * 根据场次编号，获取电影信息
     * @param fieldId
     * @return
     */
    CinemaFilmInfoVO describeFilmInfoByFieldId(String fieldId);


    /**
     * 根据场次编号，获取放映厅信息
     * @param fieldId
     * @return
     */
    FieldHallInfoVO describeHallInfoByFieldId(String fieldId);

}
