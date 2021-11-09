package com.leslie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.mapper.UserMapper;
import com.leslie.pojo.User;
import com.leslie.service.UserService;
import com.leslie.utils.BCryptPasswordEncoderUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;

    @Override
    public User login(String loginName, String loginPassword) {

        return null;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        System.out.println("checkLogin");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        User user = new User();
        user.setLoginName(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        wrapper.setEntity(user);
        List<User> list = userMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(list)){
           throw new IllegalArgumentException("用户不存在！请检查用户名是否正确");
        } else if (list.size() != 1){
           throw new IllegalArgumentException("账号异常了");
        } else if (!bCryptPasswordEncoderUtil.matches(password,list.get(0).getLoginPassword())) {
           throw new IllegalArgumentException("密码错误了");
        }
        return bCryptPasswordEncoderUtil.matches(password,list.get(0).getLoginPassword());
    }

    @Override
    public boolean checkUserExists(String registerName) {
        if (StringUtils.isBlank(registerName)){
            return true;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        User user = new User();
        user.setLoginName(registerName);
        queryWrapper.setEntity(user);
        return userMapper.selectList(queryWrapper).size() >= 1;
    }

    @Override
    public User register(User loginForm) {
        User user = new User();

        user.setLoginName(loginForm.getLoginName());
        user.setUserName(loginForm.getUserName());
        user.setLoginPassword(bCryptPasswordEncoderUtil.encode(loginForm.getLoginPassword()));
        System.out.println("user = " + user);
        int i = userMapper.insert(user);
        return null;
    }
}
