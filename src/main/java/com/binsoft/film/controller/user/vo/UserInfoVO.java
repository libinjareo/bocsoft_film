package com.binsoft.film.controller.user.vo;

import com.binsoft.film.controller.common.BaseVO;
import com.binsoft.film.controller.exception.ParameterException;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserInfoVO extends BaseVO{

    private Integer id;  //前端输入的ID
    private Integer uuid;
    private String userName;
    private String nickName;
    private Integer sex;
    private String birthday;
    private String email;
    private String phone;
    private String address;
    private String headAddress;
    private String biography;
    private String lifeState;
    private Long beginTime;
    private Long updateTime;

    public Integer getId(){
        return getUuid();
    }


    @Override
    public void checkParam() throws ParameterException {
        if(!StringUtils.hasText(this.getUserName())){
            throw new ParameterException(400,"查询不到用户名为【"+this.getUserName()+"】的用户");
        }
    }
}
