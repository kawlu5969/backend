package com.kawlul.demo.user_info.service;

import com.kawlul.demo.common.exception_handler.custom.SecurityErrorType;
import com.kawlul.demo.common.exception_handler.custom.SecurityRestApiException;
import com.kawlul.demo.user_info.Exception.UserErrorType;
import com.kawlul.demo.user_info.Exception.UserRestApiException;
import com.kawlul.demo.user_info.dto.user.UserInfo;
import com.kawlul.demo.user_info.entity.User;
import com.kawlul.demo.user_info.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public UserInfo getUserInfoByEmail(String email) {
        User user = findByUserEmail(email);
        return UserInfo.from(user);
    }

    private User findByUserEmail(String email){
        return userRepository
                .findUserByEmail(email)
                .orElseThrow(()-> new SecurityRestApiException(SecurityErrorType.USER_NOT_EXISTS));
    }

    private User findByUserName(String name){
        return userRepository
                .findUserByName(name)
                .orElseThrow(()-> new SecurityRestApiException(SecurityErrorType.USER_NOT_EXISTS));
    }

    @Override
    public Long createUser(UserInfo userInfo) {
        if(isEmailDuplicated(userInfo.getAuthUserInfo().getEmail())){
            throw new UserRestApiException(UserErrorType.NAME_DUPLICATED);
        }
        if(isNameDuplicated(userInfo.getName())){
            throw new UserRestApiException(UserErrorType.EMAIL_DUPLICATED);
        }
        User newUser = User.of(userInfo);

        return userRepository.save(newUser).getId();
    }

    private boolean isEmailDuplicated(String email){
        return userRepository.findUserByEmail(email).isPresent();
    }

    private boolean isNameDuplicated(String name){
        return userRepository.findUserByName(name).isPresent();
    }
}
