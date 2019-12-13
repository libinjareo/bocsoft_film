package com.binsoft.film.controller.cinema;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.config.properties.FilmProperties;
import com.binsoft.film.controller.cinema.vo.*;
import com.binsoft.film.controller.cinema.vo.condition.AreaResVO;
import com.binsoft.film.controller.cinema.vo.condition.BrandResVO;
import com.binsoft.film.controller.cinema.vo.condition.HallTypeResVO;
import com.binsoft.film.controller.cinema.vo.request.DescribeCinemaRequestVO;
import com.binsoft.film.controller.cinema.vo.request.FieldInfoRequestVO;
import com.binsoft.film.controller.common.BaseResponseVO;
import com.binsoft.film.service.cinema.CinemaServiceAPI;
import com.binsoft.film.service.common.ConditionTypeEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cinema")
public class CinemaController {

    @Autowired
    private FilmProperties filmProperties;

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    /**
     * 获取影院场次信息
     *
     * @param cinemaId
     * @return
     */
    @RequestMapping(value = "/getFields", method = RequestMethod.GET)
    public BaseResponseVO getFields(String cinemaId) {
        //取得影院信息
        CinemaDetailVO cinemaDetailVO = cinemaServiceAPI.describeCinemaDetails(cinemaId);
        //获取场次列表
        List<CinemaFilmVO> cinemaFilms = cinemaServiceAPI.describeFieldsAndFilmInfo(cinemaId);

        //组织响应数据
        List<Map<String, CinemaFilmVO>> filmList = Lists.newArrayList();
        cinemaFilms.stream().forEach((film) -> {
            Map<String, CinemaFilmVO> filmVOMap = Maps.newHashMap();
            filmVOMap.put("filmInfo", film);
            filmList.add(filmVOMap);
        });

        Map<String, Object> result = Maps.newHashMap();
        result.put("cinemaInfo", cinemaDetailVO);
        result.put("filmList", filmList);

        return BaseResponseVO.success(filmProperties.getImgPre(), result);

    }

    @RequestMapping(value="/getFieldInfo",method=RequestMethod.GET)
    public BaseResponseVO getFieldInfo(@RequestBody FieldInfoRequestVO requestVO){
        CinemaDetailVO cinemaDetailVO = cinemaServiceAPI.describeCinemaDetails(requestVO.getCinemaId());
        FieldHallInfoVO fieldHallInfoVO = cinemaServiceAPI.describeHallInfoByFieldId(requestVO.getFieldId());
        CinemaFilmInfoVO cinemaFilmInfoVO = cinemaServiceAPI.describeFilmInfoByFieldId(requestVO.getFieldId());

        //组织返回参数
        Map<String,Object> result = Maps.newHashMap();
        result.put("filmInfo",cinemaFilmInfoVO);
        result.put("cinemaInfo",cinemaDetailVO);
        result.put("hallInfo",fieldHallInfoVO);

        return BaseResponseVO.success(filmProperties.getImgPre(),result);

    }


    @RequestMapping(value="/getCinemas",method=RequestMethod.GET)
    public BaseResponseVO getCinemas(DescribeCinemaRequestVO requestVO){
        Page<CinemaVO> cinemaVOPage = cinemaServiceAPI.describeCinemaInfo(requestVO);

        //组织返回内容
        Map<String,Object> result = Maps.newHashMap();
        result.put("filmInfo",cinemaVOPage.getRecords());

        return BaseResponseVO.success(cinemaVOPage.getCurrent(),cinemaVOPage.getPages(),filmProperties.getImgPre(),result);

    }

    @RequestMapping(value="/getCondition",method=RequestMethod.GET)
    public BaseResponseVO getConditon(
            @RequestParam(name="brandId",required = false,defaultValue = "99") Integer brandId,
            @RequestParam(name="hallType",required = false,defaultValue = "99") Integer hallType,
            @RequestParam(name="areaId",required = false,defaultValue = "99") Integer areaId
    ){


        //有效性验证
        if(!cinemaServiceAPI.checkCondition(brandId, ConditionTypeEnum.BRAND.getType())){
            brandId=99;
        }
        if(!cinemaServiceAPI.checkCondition(hallType,ConditionTypeEnum.HALLTYPE.getType())){
            hallType = 99;
        }
        if(!cinemaServiceAPI.checkCondition(areaId,ConditionTypeEnum.AREA.getType())){
            areaId=99;
        }
        //获取结果
        List<BrandResVO> brandResVOS = cinemaServiceAPI.describeBrandConditions(brandId);
        List<AreaResVO> areaResVOS = cinemaServiceAPI.describeAreaConditions(areaId);
        List<HallTypeResVO> hallTypeResVOS = cinemaServiceAPI.describeHallTypeConditions(hallType);

        //组织响应数据
        Map<String,Object> result = Maps.newHashMap();
        result.put("brandList",brandResVOS);
        result.put("areaList",areaResVOS);
        result.put("halltypeList",hallTypeResVOS);

        return BaseResponseVO.success(result);


    }
}
