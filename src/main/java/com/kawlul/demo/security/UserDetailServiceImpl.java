package com.kawlul.demo.security;

import com.kawlul.demo.user_info.entity.User;
import com.kawlul.demo.user_info.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    /***
     * 이메일을 통해 유저를 찾고 UserDetails 객체를 반환한다.
     * @param userEmail
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User findUser = userRepository
                .findUserByEmail(userEmail)
                .orElseThrow(()-> new UsernameNotFoundException("유저가 존재하지 않습니다."));

        return new UserDetailsImpl(findUser, findUser.getEmail());
    }
}
