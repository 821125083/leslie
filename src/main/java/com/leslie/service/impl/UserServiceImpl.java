package com.leslie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.mapper.UserMapper;
import com.leslie.pojo.User;
import com.leslie.service.UserService;
import com.leslie.utils.BCryptPasswordEncoderUtil;
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
        } else if (!bCryptPasswordEncoderUtil.matches(list.get(0).getLoginPassword(),encoder.encode(password))) {
           throw new IllegalArgumentException("密码错误了");
        }
        return password.equals(list.get(0).getLoginPassword());
    }
}
