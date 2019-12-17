package com.binsoft.film.service.film;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.binsoft.film.controller.film.vo.request.DescribeFilmListReqVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.ActorResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.FilmDetailResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.ImagesResultVO;
import com.binsoft.film.controller.film.vo.response.index.BannerInfoResultVO;
import com.binsoft.film.controller.film.vo.response.index.HotFilmListResultVO;
import com.binsoft.film.controller.film.vo.response.index.RankFilmListResultVO;
import com.binsoft.film.controller.film.vo.response.index.SoonFilmListResultVO;
import com.binsoft.film.dao.entity.FilmInfoT;
import com.binsoft.film.service.common.exception.CommonServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class FilmServiceAPITest {

    @Autowired
    private FilmServiceAPI filmServiceAPI;

    @Test
    void describeBanners() {
        try {
            List<BannerInfoResultVO> result = filmServiceAPI.describeBanners();
            result.stream().forEach(System.out::println);
        } catch (CommonServiceException e) {
            e.printStackTrace();
        }


    }

    @Test
    void describeHotFilms() throws CommonServiceException {
        List<HotFilmListResultVO> hotFilmListResultVOS = filmServiceAPI.describeHotFilms();
        hotFilmListResultVOS.parallelStream().forEach(
                System.out::println
        );


    }

    @Test
    void describeSoonFilms() throws CommonServiceException {
        List<SoonFilmListResultVO> soonFilmListResultVOS = filmServiceAPI.describeSoonFilms();
        soonFilmListResultVOS.parallelStream().forEach(System.out::println);

    }

    @Test
    void boxRandFilms() throws CommonServiceException {
        List<RankFilmListResultVO> rankFilmListResultVOS = filmServiceAPI.boxRandFilms();
        rankFilmListResultVOS.parallelStream().forEach(System.out::println);
    }

    @Test
    void expectRandFilms() throws CommonServiceException {
        List<RankFilmListResultVO> rankFilmListResultVOS = filmServiceAPI.expectRandFilms();
        rankFilmListResultVOS.parallelStream().forEach(System.out::println);

    }

    @Test
    void topRandFilms() throws CommonServiceException {
        List<RankFilmListResultVO> rankFilmListResultVOS = filmServiceAPI.topRandFilms();
        rankFilmListResultVOS.parallelStream().forEach(System.out::println);

    }

    @Test
    void checkCondition() throws CommonServiceException {
        String conditionId = "5";
        String type = "year";
        String checkCondition = filmServiceAPI.checkCondition(conditionId, type);
        System.out.println("checkCondition=" + checkCondition);
    }


    @Test
    void describeCatInfos() {
    }

    @Test
    void describeSourceInfos() {
    }

    @Test
    void describeYearInfos() {
    }

    @Test
    void describeFilms() throws CommonServiceException {
        DescribeFilmListReqVO reqVO = new DescribeFilmListReqVO();

        IPage<FilmInfoT> filmInfoTIPage = filmServiceAPI.describeFilms(reqVO);

        System.out.println(filmInfoTIPage.getCurrent() + " , " + filmInfoTIPage.getPages() + " , " + filmInfoTIPage.getRecords().size());
    }

    @Test
    void describeFilmDetails() throws CommonServiceException {
        String searchStr = "药神";
        String searchType = "1";
        FilmDetailResultVO filmDetailResultVO = filmServiceAPI.describeFilmDetails(searchStr, searchType);
        System.out.println("describeFilmDetails: " + filmDetailResultVO);

    }

    @Test
    void describeFilmBio() throws CommonServiceException {

        String filmId = "2";
        String content = filmServiceAPI.describeFilmBiography(filmId);
        System.out.println("describeFilmBio:" + content);

    }

    @Test
    void describeFilmImages() throws CommonServiceException{
        String filmId = "2";
        ImagesResultVO imagesResultVO = filmServiceAPI.describeFilmImages(filmId);
        System.out.println("describeFilmImages:" + imagesResultVO);

    }

    @Test
    void describeDirector() throws CommonServiceException {
        String filmId = "2";
        ActorResultVO actorResultVO = filmServiceAPI.describeDirector(filmId);
        System.out.println("describeDirector: " + actorResultVO);


    }

    @Test
    void describeActors() throws CommonServiceException {
        String filmId = "2";
        List<ActorResultVO> actorResultVOS = filmServiceAPI.describeActors(filmId);
        actorResultVOS.stream().forEach(System.out::println);

    }

}