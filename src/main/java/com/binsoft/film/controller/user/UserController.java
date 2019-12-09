package com.binsoft.film.controller.user;

import com.binsoft.film.controller.common.BaseResponseVO;
import com.binsoft.film.controller.common.TraceUtil;
import com.binsoft.film.controller.exception.FilmException;
import com.binsoft.film.controller.exception.ParameterException;
import com.binsoft.film.controller.user.vo.EnrollUserVO;
import com.binsoft.film.controller.user.vo.UserInfoVO;
import com.binsoft.film.service.common.exception.CommonServiceException;
import com.binsoft.film.service.user.UserServiceAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/film/user/")
@Api("用户模块API")
public class UserController {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @ApiOperation(value="用户名重复性验证",notes = "用户名重复性验证")
    @ApiImplicitParam(name = "username",value="待验证的用户名称",paramType ="query",required = true,dataType = "string")
    @RequestMapping(value = "check", method = RequestMethod.POST)
    public BaseResponseVO checkUser(String username) throws CommonServiceException, FilmException {

        if (!StringUtils.hasText(username)) {
            throw new FilmException(1, "用户名不能为空！"); //已封装统一异常处理
        }

        boolean hasUserName = userServiceAPI.checkUserName(username);
        if (hasUserName) {
            return BaseResponseVO.failed("用户名已存在");
        } else {
            return BaseResponseVO.success();
        }
    }

    @ApiOperation(value="用户信息注册",notes = "用户注册：用户名、密码和手机号必须填写")
    @ApiImplicitParam(name="enrollUserVO",value="注册用户信息",required = true,dataType = "EnrollUserVO")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public BaseResponseVO register(@RequestBody EnrollUserVO enrollUserVO) throws ParameterException, CommonServiceException {
        enrollUserVO.checkParam();
        userServiceAPI.userEnroll(enrollUserVO);

        return BaseResponseVO.success();
    }

    @ApiOperation(value="查询用户信息",notes = "查询用户信息,需要从线程上下文环境中获取用户ID")
    @ApiImplicitParam(paramType = "query")
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public BaseResponseVO describeUserInfo() throws CommonServiceException, ParameterException {
        String userId = TraceUtil.getUserId();

        UserInfoVO userInfoVO = userServiceAPI.describeUserInfo(userId);

        userInfoVO.checkParam();

        return BaseResponseVO.success(userInfoVO);
    }

    @ApiOperation(value="更改用户信息",notes = "更改用户信息")
    @ApiImplicitParam(name="userInfoVO",value="要更改的用户信息",paramType = "body",required = true,dataType = "UserInfoVO")
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public BaseResponseVO updateUserInfo(@RequestBody UserInfoVO userInfoVO) throws ParameterException, CommonServiceException {

        userInfoVO.checkParam();

        UserInfoVO result = userServiceAPI.updateUserInfo(userInfoVO);

        userInfoVO.checkParam();

        return BaseResponseVO.success(result);
    }

    @ApiOperation(value="用户退出",notes = "用户退出")
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public BaseResponseVO logout() throws CommonServiceException, ParameterException {
        String userId = TraceUtil.getUserId();

        //清理缓存
        return BaseResponseVO.success();
    }

}
