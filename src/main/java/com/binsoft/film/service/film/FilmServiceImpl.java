package com.binsoft.film.service.film;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.common.utils.DateUtil;
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
import com.binsoft.film.dao.entity.*;
import com.binsoft.film.dao.mapper.*;
import com.binsoft.film.service.common.exception.CommonServiceException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmServiceAPI {

    @Autowired
    private FilmSourceDictTMapper sourceDictTMapper;

    @Autowired
    private FilmYearDictTMapper yearDictTMapper;

    @Autowired
    private FilmCatDictTMapper filmCatDictTMapper;

    @Autowired
    private FilmBannerTMapper filmBannerTMapper;

    @Autowired
    private FilmInfoTMapper filmInfoTMapper;
    @Autowired
    private FilmDetailTMapper filmDetailTMapper;

    @Autowired
    private FilmActorTMapper actorTMapper;
    @Autowired
    private FilmActorRelaTMapper actorRelaTMapper;

    @Override
    public List<BannerInfoResultVO> describeBanners() throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_valid", 0);

        List<FilmBannerT> banners = filmBannerTMapper.selectList(queryWrapper);

        List<BannerInfoResultVO> result = Lists.newArrayList();

        banners.parallelStream().forEach((banner) -> {
            BannerInfoResultVO bannerInfoResultVO = new BannerInfoResultVO();
            bannerInfoResultVO.setBannerId(banner.getUuid() + "");
            bannerInfoResultVO.setBannerUrl(banner.getBannerUrl());
            bannerInfoResultVO.setBannerAddress(banner.getBannerAddress());

            result.add(bannerInfoResultVO);
        });

        return result;
    }

    @Override
    public List<HotFilmListResultVO> describeHotFilms() throws CommonServiceException {

        //页面只显示8条记录
        Page<FilmInfoT> page = new Page<>(1, 8);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status", "1");

        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);

        List<HotFilmListResultVO> results = Lists.newArrayList();
        iPage.getRecords().stream().forEach((film) -> {
            HotFilmListResultVO result = new HotFilmListResultVO();

            result.setFilmId(film.getUuid() + "");
            result.setImgAddress(film.getImgAddress());
            result.setFilmType(film.getFilmType() + "");
            result.setFilmScore(film.getFilmScore());
            result.setFilmName(film.getFilmName());

            results.add(result);
        });
        return results;
    }

    @Override
    public List<SoonFilmListResultVO> describeSoonFilms() throws CommonServiceException {
        Page<FilmInfoT> page = new Page<>(1, 8);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status", "2");

        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);

        List<SoonFilmListResultVO> result = Lists.newArrayList();

        iPage.getRecords().stream().forEach((film) -> {
            SoonFilmListResultVO vo = new SoonFilmListResultVO();

            vo.setShowTime(DateUtil.convertToString(film.getFilmTime()));
            vo.setImgAddress(film.getImgAddress());
            vo.setFilmType(film.getFilmType() + "");
            vo.setFilmName(film.getFilmName());
            vo.setFilmId(film.getUuid() + "");
            vo.setExpectNum(film.getFilmPresalenum() + "");

            result.add(vo);
        });

        return result;
    }

    @Override
    public int describeIndexFilmNum(String filmType) throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        if ("2".equals(filmType)) {
            queryWrapper.eq("film_status", "2");
        } else {
            queryWrapper.eq("film_status", "1");
        }
        return filmInfoTMapper.selectCount(queryWrapper);
    }

    @Override
    public List<RankFilmListResultVO> boxRandFilms() throws CommonServiceException {

        Page<FilmInfoT> page = new Page<>(1, 10);
        // page.setDesc("film_box_office");

        OrderItem filmScoreOrderItem = OrderItem.desc("film_box_office");
        page.addOrder(filmScoreOrderItem);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status", "1");

        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);

        List<RankFilmListResultVO> results = Lists.newArrayList();

        iPage.getRecords().stream().forEach((film) -> {
            RankFilmListResultVO result = new RankFilmListResultVO();

            result.setScore(film.getFilmScore());
            result.setImgAddress(film.getImgAddress());
            result.setFilmName(film.getFilmName());
            result.setFilmId(film.getUuid() + "");
            result.setExpectNum(film.getFilmPresalenum() + "");
            result.setBoxNum(film.getFilmBoxOffice() + "");

            results.add(result);
        });
        return results;
    }

    @Override
    public List<RankFilmListResultVO> expectRandFilms() throws CommonServiceException {

        Page<FilmInfoT> page = new Page<>(1, 10);

        OrderItem filmScoreOrderItem = OrderItem.desc("film_preSaleNum");
        page.addOrder(filmScoreOrderItem);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status", "2");

        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);

        List<RankFilmListResultVO> results = Lists.newArrayList();

        iPage.getRecords().stream().forEach((film) -> {
            RankFilmListResultVO result = new RankFilmListResultVO();

            result.setScore(film.getFilmScore());
            result.setImgAddress(film.getImgAddress());
            result.setFilmName(film.getFilmName());
            result.setFilmId(film.getUuid() + "");
            result.setExpectNum(film.getFilmPresalenum() + "");
            result.setBoxNum(film.getFilmBoxOffice() + "");

            results.add(result);
        });
        return results;
    }

    @Override
    public List<RankFilmListResultVO> topRandFilms() throws CommonServiceException {
        Page<FilmInfoT> page = new Page<>(1, 10);

        OrderItem filmScoreOrderItem = OrderItem.desc("film_score");
        page.addOrder(filmScoreOrderItem);


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_status", "1");

        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(page, queryWrapper);

        List<RankFilmListResultVO> results = Lists.newArrayList();

        iPage.getRecords().stream().forEach((film) -> {
            RankFilmListResultVO result = new RankFilmListResultVO();

            result.setScore(film.getFilmScore());
            result.setImgAddress(film.getImgAddress());
            result.setFilmName(film.getFilmName());
            result.setFilmId(film.getUuid() + "");
            result.setExpectNum(film.getFilmPresalenum() + "");
            result.setBoxNum(film.getFilmBoxOffice() + "");

            results.add(result);
        });
        return results;
    }

    @Override
    public String checkCondition(String conditionId, String type) throws CommonServiceException {
        switch (type) {
            case "source":
                if ("99".equals(conditionId)) {
                    return conditionId;
                }
                FilmSourceDictT filmSourceDictT = sourceDictTMapper.selectById(conditionId);
                if (filmSourceDictT != null && StringUtils.hasText(filmSourceDictT.getUuid() + "")) {
                    return conditionId;
                } else {
                    return "99";
                }

            case "year":
                if ("99".equals(conditionId)) {
                    return conditionId;
                }
                FilmYearDictT filmYearDictT = yearDictTMapper.selectById(conditionId);
                if (filmYearDictT != null && StringUtils.hasText(filmYearDictT.getUuid() + "")) {
                    return conditionId;
                } else {
                    return "99";
                }
            case "cat":
                if ("99".equals(conditionId)) {
                    return conditionId;
                }
                FilmCatDictT filmCatDictT = filmCatDictTMapper.selectById(conditionId);
                if (filmCatDictT != null && StringUtils.hasText(filmCatDictT.getUuid() + "")) {
                    return conditionId;
                } else {
                    return "99";
                }
            default:
                throw new CommonServiceException(404, "无效 conditionType!");

        }

    }

    @Override
    public List<CatInfoResultVO> describeCatInfos(String catId) throws CommonServiceException {

        List<FilmCatDictT> filmCatDictTS = filmCatDictTMapper.selectList(null);

        List<CatInfoResultVO> results = filmCatDictTS.stream().map((data) -> {
            CatInfoResultVO result = new CatInfoResultVO();
            result.setCatId(data.getUuid() + "");
            result.setCatName(data.getShowName());

            if (catId.equals(data.getUuid() + "")) {
                result.setIsActive("true");
            } else {
                result.setIsActive("false");
            }
            return result;
        }).collect(Collectors.toList());


        return results;
    }

    @Override
    public List<SourceInfoResultVO> describeSourceInfos(String sourceId) throws CommonServiceException {
        List<FilmSourceDictT> filmSourceDictTS = sourceDictTMapper.selectList(null);

        List<SourceInfoResultVO> results = filmSourceDictTS.stream().map((data) -> {
            SourceInfoResultVO result = new SourceInfoResultVO();
            result.setSourceId(data.getUuid() + "");
            result.setSourceName(data.getShowName());

            if (sourceId.equals(data.getUuid() + "")) {
                result.setIsActive("true");
            } else {
                result.setIsActive("false");
            }
            return result;
        }).collect(Collectors.toList());


        return results;
    }

    @Override
    public List<YearInfoResultVO> describeYearInfos(String yearId) throws CommonServiceException {
        List<FilmYearDictT> filmYearDictTS = yearDictTMapper.selectList(null);
        List<YearInfoResultVO> results = filmYearDictTS.stream().map((data) -> {
            YearInfoResultVO result = new YearInfoResultVO();
            result.setYearId(data.getUuid() + "");
            result.setYearName(data.getShowName());
            if (yearId.equals(data.getUuid() + "")) {
                result.setIsActive("true");
            } else {
                result.setIsActive("false");
            }
            return result;
        }).collect(Collectors.toList());

        return results;
    }

    @Override
    public IPage<FilmInfoT> describeFilms(DescribeFilmListReqVO filmListReqVO) throws CommonServiceException {
        Page<FilmInfoT> infoPage = new Page<>(Long.parseLong(filmListReqVO.getNowPage()), Long.parseLong(filmListReqVO.getPageSize()));
        //排序方式 1-按照热度，2-按照时间，3-按照评价
//        Map<String,String> sortMap = Maps.newHashMap();
//        sortMap.put("1","film_preSaleNum");
//        sortMap.put("2","film_time");
//        sortMap.put("3","film_score");
//        infoPage.setDesc(sortMap.get(filmListReqVO.getSortId()));

        List<OrderItem> descs = OrderItem.descs("film_preSaleNum", "film_time", "film_score");
        int sortId = Integer.parseInt(filmListReqVO.getSortId());
        infoPage.addOrder(descs.get(sortId - 1));

        QueryWrapper queryWrapper = new QueryWrapper();
        //待搜索条件,1-正在热映 2-即将上映 3-经典
        queryWrapper.eq("film_status", filmListReqVO.getShowType());

        if (!"99".equals(filmListReqVO.getSourceId())) {
            queryWrapper.eq("film_source", filmListReqVO.getSortId());
        }

        if (!"99".equals(filmListReqVO.getYearId())) {
            queryWrapper.eq("film_date", filmListReqVO.getYearId());
        }

        if (!"99".equals(filmListReqVO.getCatId())) {
            queryWrapper.like("film_cats", "#" + filmListReqVO.getCatId() + "#");
        }

        IPage<FilmInfoT> iPage = filmInfoTMapper.selectPage(infoPage, queryWrapper);

        return iPage;
    }

    @Override
    public FilmDetailResultVO describeFilmDetails(String searchStr, String searchType) throws CommonServiceException {

        FilmDetailResultVO result;
        if ("0".equals(searchType)) {//按照编号
            result = filmInfoTMapper.describeFilmDetailByFilmId(searchStr);
        } else {
            result = filmInfoTMapper.describeFilmDataillByFilmName(searchStr);
        }

        return result;
    }

    @Override
    public String describeFilmBiography(String filmId) throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_id", filmId);


        String biography = "";

        List<FilmDetailT> list = filmDetailTMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0) {
            FilmDetailT detailT = list.get(0);
            biography = detailT.getBiography();
        }
        return biography;
    }

    @Override
    public ImagesResultVO describeFilmImages(String filmId) throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_id", filmId);

        List<FilmDetailT> list = filmDetailTMapper.selectList(queryWrapper);

        ImagesResultVO imagesResultVO = new ImagesResultVO();

        if (list != null && list.size() > 0) {
            FilmDetailT detailT = list.get(0);
            String filmImgs = detailT.getFilmImgs();
            String[] images = filmImgs.split(",");
            if (images != null && images.length == 5) {
                imagesResultVO.setMainImg(images[0]);
                imagesResultVO.setImg01(images[1]);
                imagesResultVO.setImg02(images[2]);
                imagesResultVO.setImg03(images[3]);
                imagesResultVO.setImg04(images[4]);
            } else {
                throw new CommonServiceException(500, "电影图片异常!");
            }

        }
        return imagesResultVO;
    }

    @Override
    public ActorResultVO describeDirector(String filmId) throws CommonServiceException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("film_id", filmId);

        String directorId = "";
        List<FilmDetailT> list = filmDetailTMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0) {
            FilmDetailT detailT = list.get(0);
            directorId = detailT.getDirectorId() + "";

        }

        ActorResultVO result = new ActorResultVO();
        if (StringUtils.hasText(directorId)) {
            FilmActorT filmActorT = actorTMapper.selectById(directorId);
            result.setDirectorName(filmActorT.getActorName());
            result.setImgAddress(filmActorT.getActorImg());
        }

        return result;
    }

    @Override
    public List<ActorResultVO> describeActors(String filmId) throws CommonServiceException {
        return actorTMapper.describeActorsByFilmId(filmId);
    }
}
