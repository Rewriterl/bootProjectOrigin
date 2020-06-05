package com.lopponia.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Encrypt {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private final Object salt =
            "be5e0323a9195ade5f56695ed9f2eb6b036f3e6417115d0cbe2fb9d" +
                    "74d8740406838dc84f152014b39a2414fb3530a40bc028a9e87642bd03cf5c36a1f70801e";

    public Encrypt() {
    }

    public String encode(String rawPass) {
        String result = null;
        try {
            String algorithm = "MD5";
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ignored) {
        }
        return result;
    }

    private String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }

        if ("".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte value : b) {
            resultSb.append(byteToHexString(value));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        Encrypt encoderMd5 = new Encrypt();//或者"SHA-256"
        String encode = encoderMd5.encode("pm0001");
        System.out.println("加密后的密码：" + encode);
//        boolean passwordValid = encoderMd5.isPasswordValid("c21feb87d79fd42e4336e4c231785ff9", "test");
//        System.out.println(passwordValid);

        // sha加密
//        Encrypt encoderSha = new Encrypt("SHA");
//        String pass2 = encoderSha.encode("test");
//        System.out.println(pass2);
//        boolean passwordValid2 = encoderSha.isPasswordValid("409cf43cbdc92e1979018b2e2fdc60c7f07673e9", "test");
//        System.out.println(passwordValid2);
    }
}