//package com.lopponia.test;
//
//import com.lopponia.bean.Token;
//import com.lopponia.bean.User;
//import com.lopponia.service.UserService;
//import com.lopponia.utils.TokenUtil;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class TokenTest {
//    @Test
//    //Token加解密测试
//    public void test() {
//        TokenUtil tku = new TokenUtil();
//        Token tk = new Token();
//        User user = new User();
//        user.setUser_id(1);
//        user.setUser_code("m0001");
//        user.setUser_name("小韩");
//        user.setUser_password("42a12832ff1bbba87e59e333af028fd4");
//        user.setUser_role("admin");
//        user.setUser_state(1);
//        String result = tku.creatToken(user.getUser_id().toString(), user.getUser_role());
//        System.out.println(result);
//        tk = tku.getTokenData(result);
//        System.out.println(tk.toString());
//    }
//}