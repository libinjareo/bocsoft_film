package com.binsoft.film.controller.user;

import com.binsoft.film.controller.common.BaseResponseVO;
import com.binsoft.film.controller.exception.FilmException;
import com.binsoft.film.service.common.exception.CommonServiceException;
import com.binsoft.film.service.user.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/film/user/")
public class UserController {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public BaseResponseVO checkUser(String username) throws CommonServiceException, FilmException {

        if (!StringUtils.hasText(username)) {
            throw new FilmException(1, "用户名不能为空！");
        }

        boolean hasUserName = userServiceAPI.checkUserName(username);
        if (hasUserName) {
            return BaseResponseVO.failed("用户名已存在");
        } else {
            return BaseResponseVO.success();
        }
    }

}
