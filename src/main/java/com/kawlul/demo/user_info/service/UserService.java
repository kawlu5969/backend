package com.kawlul.demo.user_info.service;

import com.kawlul.demo.user_info.dto.user.AuthUserInfo;
import com.kawlul.demo.user_info.dto.user.UserInfo;
import com.kawlul.demo.user_info.entity.User;

public interface UserService {
    public UserInfo getUserInfoByEmail(String email);
    public Long createUser(UserInfo userInfo);
}
