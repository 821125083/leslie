package com.leslie.controller;

import com.leslie.mapper.UserMapper;
import com.leslie.pojo.User;
import com.leslie.service.UserService;
import com.leslie.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @RequestMapping("getCurrentUser")
    @ResponseBody
    public User getCurrentUser(){

        return userMapper.selectById(1L);
    }

    @RequestMapping("register")
    @ResponseBody
    public ResultVO register(@RequestBody User loginForm){
        boolean exists = userService.checkUserExists(loginForm.getLoginName());
        if (exists){
            return ResultVO.error("错误");
        }
        User user = userService.register(loginForm);
        return ResultVO.success(user);
    }
}
