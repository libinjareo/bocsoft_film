package com.binsoft.film.dao.mapper;


import com.binsoft.film.controller.cinema.vo.CinemaFilmInfoVO;
import com.binsoft.film.controller.cinema.vo.CinemaFilmVO;
import com.binsoft.film.controller.cinema.vo.FieldHallInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmFieldTMapperTest {

    @Autowired
    private FilmFieldTMapper filmFieldTMapper;

    @Test
    public void describeFieldListTests() {
        List<CinemaFilmVO> cinemaFilmVOS = filmFieldTMapper.describeFieldList("1");
        System.out.println(cinemaFilmVOS);
    }

    @Test
    public void describeFilmInfoTest(){
        CinemaFilmInfoVO cinemaFilmInfoVO = filmFieldTMapper.describeFilmInfoByFieldId("1");
        System.out.println(cinemaFilmInfoVO);
    }

    @Test
    public void describeHallInfoTest(){
        FieldHallInfoVO fieldHallInfoVO = filmFieldTMapper.describeHallInfo("1");
        System.out.println(fieldHallInfoVO);

    }
}