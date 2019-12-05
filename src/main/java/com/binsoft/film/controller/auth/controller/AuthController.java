package com.binsoft.film.controller.auth.controller;

import com.binsoft.film.controller.auth.controller.vo.AuthRequestVO;
import com.binsoft.film.controller.auth.controller.vo.AuthResponseVO;
import com.binsoft.film.controller.auth.util.JwtTokenUtil;
import com.binsoft.film.controller.common.BaseResponseVO;
import com.binsoft.film.controller.exception.ParameterException;
import com.binsoft.film.service.common.exception.CommonServiceException;
import com.binsoft.film.service.user.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public BaseResponseVO auth(@RequestBody AuthRequestVO authRequestVO) throws ParameterException, CommonServiceException {
        authRequestVO.checkParam();

        boolean isValid = userServiceAPI.useAuth(authRequestVO.getUserName(), authRequestVO.getPassword());

        if (isValid) {
            String randomKey = jwtTokenUtil.getRandomKey();
            String token = jwtTokenUtil.generateToken(authRequestVO.getUserName(), randomKey);

            AuthResponseVO authResponseVO = AuthResponseVO.builder().randomKey(randomKey).token(token).build();
            return BaseResponseVO.success(authResponseVO);
        } else {
            return BaseResponseVO.failed(1, "用户名或密码错误！");
        }

    }

}
