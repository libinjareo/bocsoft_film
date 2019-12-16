package com.binsoft.film.service.film;

import com.binsoft.film.controller.film.vo.request.DescribeFilmListReqVO;
import com.binsoft.film.controller.film.vo.response.condition.CatInfoResultVO;
import com.binsoft.film.controller.film.vo.response.condition.SourceInfoResultVO;
import com.binsoft.film.controller.film.vo.response.condition.YearInfoResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.ActorResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.FilmDetailResultVO;
import com.binsoft.film.controller.film.vo.response.filmdetail.ImagesResultVO;
import com.binsoft.film.controller.film.vo.response.films.DescribeFilmListResultVO;
import com.binsoft.film.controller.film.vo.response.index.BannerInfoResultVO;
import com.binsoft.film.controller.film.vo.response.index.HotFilmListResultVO;
import com.binsoft.film.controller.film.vo.response.index.RankFilmListResultVO;
import com.binsoft.film.controller.film.vo.response.index.SoonFilmListResultVO;
import com.binsoft.film.service.common.exception.CommonServiceException;

import java.util.List;

public interface FilmServiceAPI {

    /**
     * Banner信息获取
     *
     * @return
     * @throws CommonServiceException
     */
    List<BannerInfoResultVO> describeBanners() throws CommonServiceException;

    /**
     * 获取热映影片
     *
     * @return
     * @throws CommonServiceException
     */
    List<HotFilmListResultVO> describeHotFilms() throws CommonServiceException;

    /**
     * 获取即将上映影片
     *
     * @return
     * @throws CommonServiceException
     */
    List<SoonFilmListResultVO> describeSoonFilms() throws CommonServiceException;

    /**
     * 票房排行
     *
     * @return
     * @throws CommonServiceException
     */
    List<RankFilmListResultVO> boxRandFilms() throws CommonServiceException;

    /**
     * 期待排行
     *
     * @return
     * @throws CommonServiceException
     */
    List<RankFilmListResultVO> expectRandFilms() throws CommonServiceException;

    /**
     * Top100排行
     *
     * @return
     * @throws CommonServiceException
     */
    List<RankFilmListResultVO> topRandFilms() throws CommonServiceException;

    /**
     * 验证有效性
     *
     * @param conditionId
     * @param type
     * @return
     * @throws CommonServiceException
     */
    String checkCondition(String conditionId, String type) throws CommonServiceException;

    /**
     * 查询条件
     *
     * @param catId
     * @return
     * @throws CommonServiceException
     */
    List<CatInfoResultVO> describeCatInfos(String catId) throws CommonServiceException;

    List<SourceInfoResultVO> describeSourceInfos(String sourceId) throws CommonServiceException;

    List<YearInfoResultVO> describeYearInfos(String yearId) throws CommonServiceException;

    /**
     * 获取电影列表信息
     *
     * @param filmListReqVO
     * @return
     * @throws CommonServiceException
     */
    List<DescribeFilmListResultVO> describeFilms(DescribeFilmListReqVO filmListReqVO) throws CommonServiceException;

    /**
     * 获取电影详情
     *
     * @param searchStr
     * @param searchType 0-按照编号查询 1-按名称模糊查询
     * @return
     * @throws CommonServiceException
     */
    FilmDetailResultVO describeFilmDetails(String searchStr, String searchType) throws CommonServiceException;

    /**
     * 获取影片描述信息
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    String describeFilmBio(String filmId) throws CommonServiceException;

    /**
     * 获取影片图片信息
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    ImagesResultVO describeFilmImages(String filmId) throws CommonServiceException;

    /**
     * 获取导演信息
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    ActorResultVO describeDirector(String filmId) throws CommonServiceException;

    /**
     * 获取演员信息
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    List<ActorResultVO> describeActors(String filmId) throws CommonServiceException;
}
