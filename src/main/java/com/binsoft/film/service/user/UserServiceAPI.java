package com.binsoft.film.service.user;

import com.binsoft.film.controller.user.vo.EnrollUserVO;
import com.binsoft.film.controller.user.vo.UserInfoVO;
import com.binsoft.film.service.common.exception.CommonServiceException;

public interface UserServiceAPI {

    /**
     * 用户登记
     *
     * @param enrollUserVO
     * @throws CommonServiceException
     */
    void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException;

    /**
     * 验证用户名是否存在
     *
     * @param userName
     * @return
     * @throws CommonServiceException
     */
    boolean checkUserName(String userName) throws CommonServiceException;

    /**
     * 用户名密码验证
     *
     * @param userName
     * @param userPwd
     * @return
     * @throws CommonServiceException
     */
    boolean useAuth(String userName, String userPwd) throws CommonServiceException;

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfoVO describeUserInfo(String userId) throws CommonServiceException;

    /**
     * 修改用户信息
     *
     * @param userInfoVO
     * @return
     * @throws CommonServiceException
     */
    UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceException;

    /**
     * 根据用户名取得用户ID
     * @param userName
     * @return
     * @throws CommonServiceException
     */
    String getUserIdByName(String userName) throws CommonServiceException;

}
