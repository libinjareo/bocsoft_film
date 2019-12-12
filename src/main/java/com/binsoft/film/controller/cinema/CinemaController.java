package com.binsoft.film.controller.cinema;

import com.binsoft.film.config.properties.FilmProperties;
import com.binsoft.film.controller.cinema.vo.CinemaDetailVO;
import com.binsoft.film.controller.cinema.vo.CinemaFilmVO;
import com.binsoft.film.controller.common.BaseResponseVO;
import com.binsoft.film.service.cinema.CinemaServiceAPI;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cinema")
public class CinemaController {

    @Autowired
    private FilmProperties filmProperties;

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    /**
     * 获取影院场次信息
     *
     * @param cinemaId
     * @return
     */
    @RequestMapping(value = "/getFields", method = RequestMethod.GET)
    public BaseResponseVO getFields(String cinemaId) {
        //取得影院信息
        CinemaDetailVO cinemaDetailVO = cinemaServiceAPI.describeCinemaDetails(cinemaId);
        //获取场次列表
        List<CinemaFilmVO> cinemaFilms = cinemaServiceAPI.describeFieldsAndFilmInfo(cinemaId);

        //组织响应数据
        List<Map<String, CinemaFilmVO>> filmList = Lists.newArrayList();
        cinemaFilms.stream().forEach((film) -> {
            Map<String, CinemaFilmVO> filmVOMap = Maps.newHashMap();
            filmVOMap.put("filmInfo", film);
            filmList.add(filmVOMap);
        });

        Map<String, Object> result = Maps.newHashMap();
        result.put("cinemaInfo", cinemaDetailVO);
        result.put("filmList", filmList);

        return BaseResponseVO.success(filmProperties.getImgPre(), result);

    }



}
