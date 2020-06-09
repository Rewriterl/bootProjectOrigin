package com.lopponia.controller;

import com.lopponia.bean.Result;
import com.lopponia.bean.User;
import com.lopponia.service.UserService;
import com.lopponia.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    RedisTemplate<Serializable, Serializable> redisTemplate;
    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @PostMapping("/login")
    public Result login(String usercode, String password) {
        User user = userService.findUser(usercode, password);
        System.out.println(user.toString());
        Result result = new Result();
        if (user != null) {
            TokenUtil tku = new TokenUtil();
            var token = tku.creatToken(user.getUser_id().toString(), user.getUser_code());
            result.setMessage(token);
            redisTemplate.opsForValue().set(token, user, 30, TimeUnit.MINUTES);
            System.out.println("取自redis" + redisTemplate.opsForValue().get(token));
            return result;
        }
        result.setMessage("账号或密码错误");
        return result;
    }

    @GetMapping("/user/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        Result result = new Result();
        System.out.println(redisTemplate.delete(token));
        result.setMessage("成功登出");
        return result;
    }
}