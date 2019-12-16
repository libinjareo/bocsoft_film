package com.binsoft.film.dao.mapper;

import com.binsoft.film.controller.film.vo.response.filmdetail.FilmDetailResultVO;
import com.binsoft.film.dao.entity.FilmInfoT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2019-12-13
 */
public interface FilmInfoTMapper extends BaseMapper<FilmInfoT> {

    /**
     * 根据FilmId获取电影的详情
     * @param filmId
     * @return
     */
    FilmDetailResultVO describeFilmDetailByFilmId(@Param("filmId") String filmId);

    /**
     * 根据电影名称获取电影的详情（模糊匹配）
     * @param filmName
     * @return
     */
    FilmDetailResultVO describeFilmDataillByFilmName(@Param("filmName") String filmName);
}
