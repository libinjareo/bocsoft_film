package com.binsoft.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.binsoft.film.dao.entity.NextUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

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

    //自定义查询
    List<NextUser> selectUsers();


    //自定义分页
    List<NextUser> selectPageUsers(IPage<NextUser> iPage);
}
