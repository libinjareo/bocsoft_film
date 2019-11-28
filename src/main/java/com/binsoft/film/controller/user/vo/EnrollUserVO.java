package com.binsoft.film.controller.user.vo;

import com.binsoft.film.controller.common.BaseVO;
import com.binsoft.film.controller.exception.ParameterException;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class EnrollUserVO extends BaseVO {

    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;


    @Override
    public void checkParam() throws ParameterException {
        if (!StringUtils.hasText(this.getUserName())) {
            throw new ParameterException(400, "用户名不能为空");
        }
        if (!StringUtils.hasText(this.getPassword())) {
            throw new ParameterException(400, "用户名不能为空");
        }
    }


}
