package com.lopponia.controller;

import com.lopponia.bean.Result;
import com.lopponia.bean.User;
import com.lopponia.service.UserService;
import com.lopponia.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @PostMapping("/login")
    public Result login(String usercode, String password) {
        User user = userService.findUser(usercode, password);
        Result result = new Result();
        if (user != null) {
            TokenUtil tku = new TokenUtil();
            result.setMessage(tku.creatToken(user.getUser_id().toString(), user.getUser_code()));
            System.out.println(result.toString());
            return result;
        }
        result.setMessage("账号或密码错误");
        return result;
    }

    // 测试接口
    @GetMapping("/user/{id}")
    public User getCustomerById(@PathVariable("id") Integer id) {
        return userService.findUser("admin", "admin");
    }

    @GetMapping("/user/Customer")
    public String toCustomer() {
        return "期待返回customer页面";
    }

    @GetMapping("/user/logout")
    public String logout() {
//        session.invalidate();
        return "登出";
    }
}