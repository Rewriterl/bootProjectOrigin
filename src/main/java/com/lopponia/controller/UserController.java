package com.lopponia.controller;

import com.lopponia.bean.Token;
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

    @PostMapping(value = "/login")
    public String login(String usercode, String password) {
        User user = userService.findUser(usercode, password);
        if (user != null) {
//            session.setAttribute("USER_SESSION", user);
//            return "redirect:customer/list.action";
            TokenUtil tku = new TokenUtil();
            String result = tku.creatToken(user.getUser_id().toString(), user.getUser_code());
            System.out.println(tku.getTokenData(result).toString());
            return result;
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