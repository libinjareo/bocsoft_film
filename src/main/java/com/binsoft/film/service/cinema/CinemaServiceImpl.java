package com.binsoft.film.service.cinema;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.controller.cinema.vo.*;
import com.binsoft.film.controller.cinema.vo.condition.AreaResVO;
import com.binsoft.film.controller.cinema.vo.condition.BrandResVO;
import com.binsoft.film.controller.cinema.vo.condition.HallTypeResVO;
import com.binsoft.film.controller.cinema.vo.request.DescribeCinemaRequestVO;
import com.binsoft.film.dao.entity.FilmCinemaT;
import com.binsoft.film.dao.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaServiceAPI {

    @Autowired
    private FilmFieldTMapper filmFieldMapper;
    @Autowired
    private FilmCinemaTMapper cinemaMapper;
    @Autowired
    private FilmHallFilmInfoTMapper filmInfoMapper;
    @Autowired
    private FilmAreaDictTMapper areaDictMapper;
    @Autowired
    private FilmHallDictTMapper hallDictMapper;
    @Autowired
    private FilmBrandDictTMapper brandDictMapper;

    @Override
    public Page<CinemaVO> describeCinemaInfo(DescribeCinemaRequestVO describeCinemaRequestVO) {

        //构建分页对象
        Page<FilmCinemaT> page = new Page<>(
                describeCinemaRequestVO.getNowpage(),
                describeCinemaRequestVO.getPageSize()
        );

        //构建查询条件
        QueryWrapper<FilmCinemaT> filmCinemaTQueryWrapper = describeCinemaRequestVO.genWrapper();

        //查询库数据
        IPage<FilmCinemaT> filmCinemaTIPage = cinemaMapper.selectPage(page, filmCinemaTQueryWrapper);

        //组织返回数据
        Page<CinemaVO> cinemaPage = new Page<>(describeCinemaRequestVO.getNowpage(), describeCinemaRequestVO.getPageSize());
        cinemaPage.setTotal(page.getTotal());

        //数据实体转换为展现层实体
        List<CinemaVO> cinemas = filmCinemaTIPage.getRecords().stream().map((data) -> {
            //数据转换
            CinemaVO cinemaVO = new CinemaVO();
            cinemaVO.setUuid(data.getUuid() + "");
            cinemaVO.setCinemaName(data.getCinemaName());
            cinemaVO.setAddress(data.getCinemaAddress());
            cinemaVO.setMinimumPrice(data.getMinimumPrice() + "");

            return cinemaVO;

        }).collect(Collectors.toList());

        cinemaPage.setRecords(cinemas);

        return cinemaPage;
    }

    @Override
    public List<BrandResVO> describeBrandConditions(int brandId) {
        return null;
    }

    @Override
    public List<AreaResVO> describeAreaConditions(int areaId) {
        return null;
    }

    @Override
    public List<HallTypeResVO> describeHallTypeConditions(int hallTypeId) {
        return null;
    }

    @Override
    public CinemaDetailVO describeCinemaDetails(String cinemaId) {
        return null;
    }

    @Override
    public Page<CinemaFilmVO> describeFieldsAndFilmInfo(String cinemaId) {
        return null;
    }

    @Override
    public CinemaFilmInfoVO describeFilmInfoByFieldId(String fieldId) {
        return null;
    }

    @Override
    public FieldHallInfoVO describeHallInfoByFieldId(String fieldId) {
        return null;
    }
}
