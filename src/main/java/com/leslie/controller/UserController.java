package com.leslie.controller;

import com.leslie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;
}
