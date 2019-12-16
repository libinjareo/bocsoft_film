package com.binsoft.film.dao.mapper;

import com.binsoft.film.controller.film.vo.response.filmdetail.ActorResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.FilmDetailResultVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class FilmInfoTMapperTest {

    @Autowired
    private FilmInfoTMapper filmInfoTMapper;

    @Autowired
    private FilmActorTMapper actorTMapper;

    @Test
    void describeFilmDetailByFilmId() {
        String filmId = "2";
        FilmDetailResultVO filmDetailResultVO = filmInfoTMapper.describeFilmDetailByFilmId(filmId);
        System.out.println("describeFilmDetailByFilmId: " + filmDetailResultVO);

    }

    @Test
    void describeFilmDataillByFilmName() {
        String filmName = "药神";
        FilmDetailResultVO filmDetailResultVO = filmInfoTMapper.describeFilmDataillByFilmName(filmName);
        System.out.println("describeFilmDataillByFilmName: " + filmDetailResultVO);

    }

    @Test
    void describeActorsByFilmId(){
        String filmId = "2";
        List<ActorResultVO> actorResultVOS = actorTMapper.describeActorsByFilmId(filmId);
        actorResultVOS.stream().forEach(System.out::println);

    }

}