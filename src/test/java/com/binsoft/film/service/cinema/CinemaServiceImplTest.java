package com.binsoft.film.service.cinema;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.controller.cinema.vo.CinemaVO;
import com.binsoft.film.controller.cinema.vo.request.DescribeCinemaRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    }

    @Test
    void describeAreaConditions() {
    }

    @Test
    void describeHallTypeConditions() {
    }

    @Test
    void describeCinemaDetails() {
    }

    @Test
    void describeFieldsAndFilmInfo() {
    }

    @Test
    void describeFilmInfoByFieldId() {
    }

    @Test
    void describeHallInfoByFieldId() {
    }

}