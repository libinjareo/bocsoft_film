package com.binsoft.film.service.user;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binsoft.film.common.utils.MD5Util;
import com.binsoft.film.common.utils.RegexUtil;
import com.binsoft.film.controller.user.vo.EnrollUserVO;
import com.binsoft.film.controller.user.vo.UserInfoVO;
import com.binsoft.film.dao.entity.NextUserT;
import com.binsoft.film.dao.mapper.NextUserTMapper;
import com.binsoft.film.service.common.exception.CommonServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserServiceAPI {

    @Autowired
    private NextUserTMapper userMapper;

    @Override
    public void userEnroll(EnrollUserVO enrollUserVO) throws CommonServiceException {
        // 1、逻辑验证

        //2、数据转换
        NextUserT nextUserT = new NextUserT();
        BeanUtils.copyProperties(enrollUserVO, nextUserT);
        nextUserT.setUserPwd(MD5Util.encrypt(enrollUserVO.getPassword()));

        //3、数据插入
        int isSuccess = userMapper.insert(nextUserT);

        //4、判断插入是否成功
        if (isSuccess != 1) {
            throw new CommonServiceException(500, "用户登记失败!");
        }
    }

    @Override
    public boolean checkUserName(String userName) throws CommonServiceException {
        AbstractWrapper queryWrapper = new QueryWrapper<NextUserT>();
        queryWrapper.eq("user_name", userName);

        int userCount = userMapper.selectCount(queryWrapper);

        return userCount == 0 ? false : true;
    }

    @Override
    public boolean useAuth(String userName, String userPwd) throws CommonServiceException {
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(userPwd)) {
            throw new CommonServiceException(500, "用户登录失败");
        }

        AbstractWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        //1、判断用户是否存在
        List<NextUserT> list = userMapper.selectList(queryWrapper);
        if (list.size() == 0) {
            return false;
        } else {
            //2、如果用户存在，则判断密码是否正确
            NextUserT nextUserT = list.get(0);
            if (MD5Util.encrypt(userPwd).equals(nextUserT.getUserPwd())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserInfoVO describeUserInfo(String userId) throws CommonServiceException {
        NextUserT nextUserT = userMapper.selectById(userId);
        if (nextUserT != null && nextUserT.getUuid() != null) {
            UserInfoVO userInfoVO = tuser2InfoVO(nextUserT);
            return userInfoVO;
        } else {
            throw new CommonServiceException(400, "编号为【" + userId + "】的用户不存在!");
        }

    }

    @Override
    public UserInfoVO updateUserInfo(UserInfoVO userInfoVO) throws CommonServiceException {
        NextUserT nextUserT = infoVO2TUser(userInfoVO);
        if (nextUserT != null && nextUserT.getUuid() != null) {
            int isSuccess = userMapper.updateById(nextUserT);
            if (isSuccess == 1) {
                UserInfoVO result = describeUserInfo(userInfoVO.getUuid() + "");
                return result;
            } else {
                throw new CommonServiceException(500, "用户信息修改失败!");
            }


        } else {
            throw new CommonServiceException(404, "用户编号为【" + userInfoVO.getUuid() + "】的用户不存在");
        }


    }

    private UserInfoVO tuser2InfoVO(NextUserT nextUserT) {
        UserInfoVO userInfoVO = new UserInfoVO();

        BeanUtils.copyProperties(nextUserT, userInfoVO);

        userInfoVO.setLifeState(nextUserT.getLifeState() + "");
        userInfoVO.setBeginTime(nextUserT.getBeginTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVO.setUpdateTime(nextUserT.getUpdateTime().toEpochSecond(ZoneOffset.of("+8")));

        return userInfoVO;
    }

    private NextUserT infoVO2TUser(UserInfoVO userInfoVO) {
        NextUserT nextUserT = new NextUserT();

        if (Optional.ofNullable(userInfoVO.getLifeState()).isPresent()) {
            if (RegexUtil.isNumeric(userInfoVO.getLifeState())) {
                nextUserT.setLifeState(Integer.parseInt(userInfoVO.getLifeState()));
            }
        }

        BeanUtils.copyProperties(userInfoVO, nextUserT);


        nextUserT.setUpdateTime(LocalDateTime.now());

        return nextUserT;

    }
}
