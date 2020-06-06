package com.lopponia.service;

import com.lopponia.bean.User;

public interface UserService {
    User findUser(String usercode, String password);
}