package com.binsoft.film.service.cinema;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.controller.cinema.vo.CinemaVO;
import com.binsoft.film.controller.cinema.vo.condition.BrandResVO;
import com.binsoft.film.controller.cinema.vo.request.DescribeCinemaRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class CinemaServiceImplTest {

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;


    @Test
    void describeCinemaInfo() {
        DescribeCinemaRequestVO describeCinemaRequestVO = new DescribeCinemaRequestVO();
        describeCinemaRequestVO.setBrandId(1);
        describeCinemaRequestVO.setDistrictId(1);
        describeCinemaRequestVO.setHallType(1);

        Page<CinemaVO> cinemaVOPage = cinemaServiceAPI.describeCinemaInfo(describeCinemaRequestVO);
        log.info("nowPage:{},totalPage:{},recordNum:{},records:{}", cinemaVOPage.getCurrent(), cinemaVOPage.getPages(), cinemaVOPage.getTotal(), cinemaVOPage.getRecords());

    }

    @Test
    void describeBrandConditions() {
        List<BrandResVO> brands = cinemaServiceAPI.describeBrandConditions(1);
        brands.stream().forEach(data -> System.out.println(data));

    }

    @Test
    void describeAreaConditions() {

    }

    @Test
    void describeHallTypeConditions() {
    }

    @Test
    void describeCinemaDetails() {
        System.out.println(cinemaServiceAPI.describeCinemaDetails("1"));
    }

    @Test
    void describeFieldsAndFilmInfo() {
        System.out.println(cinemaServiceAPI.describeFieldsAndFilmInfo("1"));
    }

    @Test
    void describeFilmInfoByFieldId() {
        System.out.println(cinemaServiceAPI.describeFilmInfoByFieldId("1"));
    }

    @Test
    void describeHallInfoByFieldId() {
        System.out.println(cinemaServiceAPI.describeHallInfoByFieldId("1"));
    }

}