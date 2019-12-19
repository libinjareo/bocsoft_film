package com.binsoft.film.controller.film;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.binsoft.film.config.properties.FilmProperties;
import com.binsoft.film.controller.common.BaseResponseVO;
import com.binsoft.film.controller.film.vo.request.DescribeFilmListReqVO;
import com.binsoft.film.controller.film.vo.response.condition.CatInfoResultVO;
import com.binsoft.film.controller.film.vo.response.condition.SourceInfoResultVO;
import com.binsoft.film.controller.film.vo.response.condition.YearInfoResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.ActorResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.FilmDetailResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.ImagesResultVO;
import com.binsoft.film.controller.film.vo.response.index.BannerInfoResultVO;
import com.binsoft.film.controller.film.vo.response.index.HotFilmListResultVO;
import com.binsoft.film.controller.film.vo.response.index.RankFilmListResultVO;
import com.binsoft.film.controller.film.vo.response.index.SoonFilmListResultVO;
import com.binsoft.film.dao.entity.FilmInfoT;
import com.binsoft.film.service.common.exception.CommonServiceException;
import com.binsoft.film.service.film.FilmServiceAPI;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmServiceAPI filmServiceAPI;

    @Autowired
    private FilmProperties filmProperties;

    /**
     * 获取首页信息
     *
     * @return
     */
    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    public BaseResponseVO describeIndex() throws CommonServiceException {

        Map<String, Object> resultMap = Maps.newHashMap();

        //banners
        List<BannerInfoResultVO> banners = filmServiceAPI.describeBanners();
        resultMap.put("banners", banners);

        //hotFilms
        List<HotFilmListResultVO> hotFilms = filmServiceAPI.describeHotFilms();
        final int hotFilmNum = filmServiceAPI.describeIndexFilmNum("1");

        Map<String, Object> hotFilmMap = Maps.newHashMap();
        hotFilmMap.put("filmInfo", hotFilms);
        hotFilmMap.put("filmNum", hotFilmNum);

        resultMap.put("hotFilms", hotFilmMap);

        //soonFilms
        List<SoonFilmListResultVO> soonFilms = filmServiceAPI.describeSoonFilms();
        int soonFilmNum = filmServiceAPI.describeIndexFilmNum("2");

        Map<String, Object> soonFilmMap = Maps.newHashMap();
        soonFilmMap.put("filmInfo", soonFilms);
        soonFilmMap.put("filmNum", soonFilmNum);

        resultMap.put("soonFilms", soonFilmMap);

        //boxRanking
        List<RankFilmListResultVO> boxRandFilms = filmServiceAPI.boxRandFilms();
        resultMap.put("boxRanking", boxRandFilms);

        //expectRanking
        List<RankFilmListResultVO> expectRandFilms = filmServiceAPI.expectRandFilms();
        resultMap.put("expectRanking", expectRandFilms);
        //top100
        List<RankFilmListResultVO> topRandFilms = filmServiceAPI.topRandFilms();
        resultMap.put("top100", topRandFilms);


        return BaseResponseVO.success(filmProperties.getImgPre(), resultMap);
    }

    /**
     * 查询条件列表
     *
     * @param cartId
     * @param sourceId
     * @param yearId
     * @return
     */
    @RequestMapping(value = "/getConditionList", method = RequestMethod.GET)
    public BaseResponseVO getConditionList(
            @RequestParam(name = "catId", required = false, defaultValue = "99") String cartId,
            @RequestParam(name = "sourceId", required = false, defaultValue = "99") String sourceId,
            @RequestParam(name = "yearId", required = false, defaultValue = "99") String yearId

    ) throws CommonServiceException {

        //检查入参
        cartId = filmServiceAPI.checkCondition(cartId, "cat");
        sourceId = filmServiceAPI.checkCondition(sourceId, "source");
        yearId = filmServiceAPI.checkCondition(yearId, "year");

        Map<String, Object> resultMap = Maps.newHashMap();

        List<CatInfoResultVO> catInfos = filmServiceAPI.describeCatInfos(cartId);
        List<SourceInfoResultVO> sourceInfos = filmServiceAPI.describeSourceInfos(sourceId);
        List<YearInfoResultVO> yearinfos = filmServiceAPI.describeYearInfos(yearId);

        resultMap.put("catInfo", catInfos);
        resultMap.put("sourceInfo", sourceInfos);
        resultMap.put("yearInfo", yearinfos);

        return BaseResponseVO.success(resultMap);
    }

    /**
     * 获取电影列表
     *
     * @param requestVO
     * @return
     */
    @RequestMapping(value = "/getFilms", method = RequestMethod.GET)
    public BaseResponseVO getFilms(DescribeFilmListReqVO requestVO) throws CommonServiceException {

        IPage<FilmInfoT> page = filmServiceAPI.describeFilms(requestVO);


        return BaseResponseVO.success(page.getCurrent(), page.getPages(), filmProperties.getImgPre(), page.getRecords());
    }

    /**
     * 电影详情
     *
     * @param searchStr
     * @param searchType
     * @return
     */
    @RequestMapping(value = "/films/{searchStr}", method = RequestMethod.GET)
    public BaseResponseVO describeFilmDetails(@PathVariable(name = "searchStr") String searchStr, String searchType) throws CommonServiceException {
        final FilmDetailResultVO filmDetailResultVO = filmServiceAPI.describeFilmDetails(searchStr, searchType);

        String filmId = filmDetailResultVO.getFilmId();

        //biography
        String biography = filmServiceAPI.describeFilmBiography(filmId);

        //actors
        Map<String, Object> actors = Maps.newHashMap();

        ActorResultVO director = filmServiceAPI.describeDirector(filmId);

        final List<ActorResultVO> actorResults = filmServiceAPI.describeActors(filmId);

        actors.put("director", director);
        actors.put("actors", actorResults);

        //imgs
        ImagesResultVO imagesResultVO = filmServiceAPI.describeFilmImages(filmId);

        filmDetailResultVO.getInfo04().put("biography", biography);
        filmDetailResultVO.getInfo04().put("actors", actors);


        filmDetailResultVO.setImgs(imagesResultVO);


        return BaseResponseVO.success(filmProperties.getImgPre(), filmDetailResultVO);
    }

}
