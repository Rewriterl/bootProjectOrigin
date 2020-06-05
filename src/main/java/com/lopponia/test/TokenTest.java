package com.lopponia.test;

import com.lopponia.bean.Token;
import com.lopponia.utils.TokenUtil;
import org.junit.Test;

public class TokenTest {
    @Test
    //Token加解密测试
    public void test() {
        TokenUtil tku = new TokenUtil();
        Token tk = new Token();

        String result = tku.creatToken("asdfghjkl", "admin");

        System.out.println(result);

        tk = tku.getTokenData(result);

        System.out.println(tk.toString());
    }
}