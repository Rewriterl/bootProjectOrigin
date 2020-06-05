package com.lopponia.controller;

import com.lopponia.bean.Customer;
import com.lopponia.bean.User;
import com.lopponia.service.UserService;
import com.lopponia.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @PostMapping(value = "/user/login")
    public String login(String usercode, String password, Model model) {
        password = new Encrypt().encode(password);
        User user = userService.findUser(usercode, password);
        if (user != null) {
//            session.setAttribute("USER_SESSION", user);
//            return "redirect:customer/list.action";
            return user.toString();
        }
//        model.addAttribute("msg", "账号或密码错误");
        return "验证失败/无此用户";
    }

    // 测试接口
    @GetMapping(value = "/user/{id}")
    public User getCustomerById(@PathVariable("id") Integer id) {
        return userService.findUser("admin", "admin");
    }

    @GetMapping(value = "/user/Customer", produces = "text/html;charset=UTF-8")
    public String toCustomer() {
        return "期待返回customer页面";
    }

    @GetMapping(value = "/user/logout", produces = "text/html;charset=UTF-8")
    public String logout() {
//        session.invalidate();
        return "登出";
    }

//    @RequestMapping(value = "/login.action", method = RequestMethod.GET)
//    public String toLogin() {
//        return "login";
//    }
}