package com.kawlul.demo.user_info.dto.user;

import com.kawlul.demo.user_info.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserInfo {
    private final AuthUserInfo authUserInfo;
    private String name;

    public static UserInfo from(User user){
        return  UserInfo
                .builder()
                .authUserInfo(AuthUserInfo.from(user))
                .name(user.getEmail())
                .build();
    }
}
