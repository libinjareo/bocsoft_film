package com.binsoft.film.dao.mapper;

import com.binsoft.film.controller.film.vo.response.filmdetail.ActorResultVO;
import com.binsoft.film.dao.entity.FilmActorT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2019-12-13
 */
public interface FilmActorTMapper extends BaseMapper<FilmActorT> {

    /**
     * 根据电影编号，获取对应的演员列表
     * @param filmId
     * @return
     */
   List<ActorResultVO> describeActorsByFilmId(@Param("filmId") String filmId);
}
