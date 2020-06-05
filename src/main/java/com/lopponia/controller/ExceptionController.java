package com.lopponia.controller;

import com.lopponia.bean.Result;
import com.lopponia.bean.ResultStatus;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.*;

/**
 * 权限异常处理，控制器都应该继承
 */

@RestController
@RequestMapping(value = "/Error")
public class ExceptionController {
    //拒绝请求
    @GetMapping(value = "/notoken")
    public Result notoken() {
        Result rs = new Result();
        System.out.println("Token无效");
        rs.setResult(ResultStatus.NOTOKEN);
        return rs;
    }

    //无权时的异常处理
    @ExceptionHandler({UnauthorizedException.class})
    @GetMapping(value = "/unauthorized")
    @ResponseBody
    public Result unauthorized() {
        Result rs = new Result();
        System.out.println("无权访问");
        rs.setResult(ResultStatus.ROLEERR);
        return rs;
    }

}

