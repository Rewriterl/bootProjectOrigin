package com.lopponia.dao;

import com.lopponia.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public User findUser(@Param("usercode") String usercode,
                         @Param("password") String password);
}
