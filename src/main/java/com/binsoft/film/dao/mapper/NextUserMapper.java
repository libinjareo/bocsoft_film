package com.binsoft.film.dao.mapper;

import com.binsoft.film.dao.entity.NextUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2019-11-22
 */
public interface NextUserMapper extends BaseMapper<NextUser> {

    void selectList();
}
