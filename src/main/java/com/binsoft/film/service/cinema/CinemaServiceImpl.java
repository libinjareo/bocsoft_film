package com.binsoft.film.service.cinema;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.controller.cinema.vo.*;
import com.binsoft.film.controller.cinema.vo.condition.AreaResVO;
import com.binsoft.film.controller.cinema.vo.condition.BrandResVO;
import com.binsoft.film.controller.cinema.vo.condition.HallTypeResVO;
import com.binsoft.film.controller.cinema.vo.request.DescribeCinemaRequestVO;
import com.binsoft.film.dao.entity.FilmAreaDictT;
import com.binsoft.film.dao.entity.FilmBrandDictT;
import com.binsoft.film.dao.entity.FilmCinemaT;
import com.binsoft.film.dao.entity.FilmHallDictT;
import com.binsoft.film.dao.mapper.*;
import com.binsoft.film.service.common.ConditionTypeEnum;
import com.google.common.collect.Lists;
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
    public boolean checkCondition(int conditionId, String conditionType) {

        ConditionTypeEnum conditionTypeEnum = ConditionTypeEnum.getTypeEnum(conditionType);

        switch (conditionTypeEnum) {
            case BRAND:
                FilmBrandDictT filmBrandDictT = brandDictMapper.selectById(conditionId);
                if (filmBrandDictT != null && filmBrandDictT.getUuid() != null) {
                    return true;
                } else {
                    return false;
                }
            case AREA:
                FilmAreaDictT filmAreaDictT = areaDictMapper.selectById(conditionId);
                if (filmAreaDictT != null && filmAreaDictT.getUuid() != null) {
                    return true;
                } else {
                    return false;
                }
            case HALLTYPE:
                FilmHallDictT filmHallDictT = hallDictMapper.selectById(conditionId);
                if (filmHallDictT != null && filmHallDictT.getUuid() != null) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;

        }

    }

    @Override
    public List<BrandResVO> describeBrandConditions(final int brandId) {

        //获取所有列表
        List<FilmBrandDictT> brands = brandDictMapper.selectList(null);
        //将对应的品牌设置为 isActive=true
        List<BrandResVO> collect = brands.stream().map((data) -> {

            BrandResVO brandResVO = new BrandResVO();

            if (brandId == data.getUuid()) {
                brandResVO.setActive(true);
            }
            brandResVO.setBrandId(brandId + "");
            brandResVO.setBrandName(data.getShowName());
            return brandResVO;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<AreaResVO> describeAreaConditions(final int areaId) {
        //获取所有列表
        List<FilmAreaDictT> filmAreaDictTS = areaDictMapper.selectList(null);
        //将对应的区域设置为isActive=true
        List<AreaResVO> collect = filmAreaDictTS.stream().map((data) -> {
            AreaResVO areaResVO = new AreaResVO();
            if (areaId == data.getUuid()) {
                areaResVO.setActive(true);
            }
            areaResVO.setAreaId(areaId + "");
            areaResVO.setAreaName(data.getShowName());
            return areaResVO;

        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<HallTypeResVO> describeHallTypeConditions(final int hallTypeId) {
        //获取所有列表
        List<FilmHallDictT> filmHallDictTS = hallDictMapper.selectList(null);

        //设置对应类型设置为isActive=true
        List<HallTypeResVO> collect = filmHallDictTS.stream().map((data) -> {
            HallTypeResVO hallTypeResVO = new HallTypeResVO();
            if (hallTypeId == data.getUuid()) {
                hallTypeResVO.setActive(true);
            }
            hallTypeResVO.setHalltypeId(hallTypeId + "");
            hallTypeResVO.setHalltypeName(data.getShowName());

            return hallTypeResVO;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public CinemaDetailVO describeCinemaDetails(String cinemaId) {
        final FilmCinemaT data = cinemaMapper.selectById(cinemaId);
        CinemaDetailVO cinemaDetailVO =
                CinemaDetailVO.builder()
                        .cinemaAddress(data.getCinemaAddress())
                        .cinemaId(data.getUuid()+"")
                        .cinemaName(data.getCinemaName())
                        .cinemaPhone(data.getCinemaPhone())
                        .imgUrl(data.getImgAddress()).build();

        return cinemaDetailVO;
    }

    @Override
    public List<CinemaFilmVO> describeFieldsAndFilmInfo(String cinemaId) {
        //有效性检测
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uuid",cinemaId);
        Integer count = cinemaMapper.selectCount(queryWrapper);
        if(count == 0){
            return Lists.newArrayList();
        }

        return filmFieldMapper.describeFieldList(cinemaId);
    }

    @Override
    public CinemaFilmInfoVO describeFilmInfoByFieldId(String fieldId) {
        return filmFieldMapper.describeFilmInfoByFieldId(fieldId);
    }

    @Override
    public FieldHallInfoVO describeHallInfoByFieldId(String fieldId) {
        return filmFieldMapper.describeHallInfo(fieldId);
    }
}
