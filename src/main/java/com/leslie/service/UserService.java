package com.leslie.service;

import com.leslie.pojo.User;

public interface UserService {

    User login(String loginName ,String loginPassword);

    boolean checkLogin(String username, String password);

    boolean checkUserExists(String registerName);

    User register(User loginForm);
}
