package com.binsoft.film.controller.user.vo;

import lombok.Data;

@Data
public class UserInfoVO {

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
}
