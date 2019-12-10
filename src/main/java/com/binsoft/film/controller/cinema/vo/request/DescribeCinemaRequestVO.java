package com.binsoft.film.controller.cinema.vo.request;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binsoft.film.controller.common.BaseVO;
import com.binsoft.film.controller.exception.ParameterException;
import com.binsoft.film.dao.entity.FilmCinemaT;
import lombok.Data;

@Data
public class DescribeCinemaRequestVO extends BaseVO {

    private Integer brandId = 99;
    private Integer hallType = 99;
    private Integer districtId = 99;
    private Integer pageSize = 20;
    private Integer nowpage = 1;


    @Override
    public void checkParam() throws ParameterException {

    }


    /**
     * 获取FilmCinemaT对象的Wrapper对象
     *
     * @return
     */
    public QueryWrapper<FilmCinemaT> genWrapper() {
        QueryWrapper<FilmCinemaT> wrapper = new QueryWrapper<>();

        if (this.getBrandId() != null && this.getBrandId() != 99) {
            wrapper.eq("brand_id", this.getBrandId());
        }
        if (this.getDistrictId() != null && this.getDistrictId() != 99) {
            wrapper.eq("area_id", this.getDistrictId());
        }
        if (this.getHallType() != null && this.getHallType() != 99) {
            wrapper.like("hall_ids", "#" + this.getHallType() + "#");
        }

        return wrapper;
    }


}
