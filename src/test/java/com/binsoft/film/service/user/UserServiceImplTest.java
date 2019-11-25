package com.binsoft.film.service.user;

import com.binsoft.film.controller.user.vo.EnrollUserVO;
import com.binsoft.film.controller.user.vo.UserInfoVO;
import com.binsoft.film.service.common.exception.CommonServiceException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void userEnroll() throws CommonServiceException {
        EnrollUserVO vo = new EnrollUserVO();
        vo.setUserName("binsoft");
        vo.setPassword("1");
        vo.setEmail("libinjareo@163.com");
        vo.setAddress("北京市西城区中南海1号院");
        vo.setPhone("11011011010");

        userService.userEnroll(vo);
    }

    @Test
    void checkUserName() throws CommonServiceException {

        String name = "binsoft";

        boolean b = userService.checkUserName(name);
        Assert.assertEquals(b,true);

    }

    @Test
    void useAuth() throws CommonServiceException {
        String name = "binsoft";
        String pwd = "1";

        boolean b = userService.useAuth(name, pwd);
        Assert.assertEquals(b,true);

    }

    @Test
    void describeUserInfo() throws CommonServiceException {

        //String userId = "5";
        String userId = "4";
        UserInfoVO userInfoVO = userService.describeUserInfo(userId);
        System.out.println(userInfoVO);

    }

    @Test
    void updateUserInfo() {
    }

}