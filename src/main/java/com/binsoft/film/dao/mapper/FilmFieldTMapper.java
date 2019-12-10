package com.binsoft.film.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.binsoft.film.controller.cinema.vo.CinemaFilmInfoVO;
import com.binsoft.film.controller.cinema.vo.CinemaFilmVO;
import com.binsoft.film.controller.cinema.vo.FieldHallInfoVO;
import com.binsoft.film.dao.entity.FilmFieldT;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2019-12-05
 */
public interface FilmFieldTMapper extends BaseMapper<FilmFieldT> {
    List<CinemaFilmVO> describeFieldList(@Param("cinemaId") String cinemaId);
    CinemaFilmInfoVO describeFilmInfoByFieldId(@Param("fieldId") String fieldId);
    FieldHallInfoVO describeHallInfo(@Param("fieldId") String fieldId);
}
