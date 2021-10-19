package com.leslie.service.impl;

import com.leslie.mapper.UserMapper;
import com.leslie.pojo.LoginUserDetail;
import com.leslie.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserAuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUserName(username);
        // todo 权限等操作
        LoginUserDetail userDetail = new LoginUserDetail(user.getId(), user.getLoginPassword(), user.getLoginName());
        System.out.println("userDetail = " + userDetail);
        return userDetail;
    }
}
