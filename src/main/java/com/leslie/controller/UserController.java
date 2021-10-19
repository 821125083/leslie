package com.leslie.controller;

import com.leslie.mapper.UserMapper;
import com.leslie.pojo.User;
import com.leslie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @RequestMapping("getCurrentUser")
    @ResponseBody
    public User getCurrentUser(){

        return userMapper.selectById(1L);
    }
}
