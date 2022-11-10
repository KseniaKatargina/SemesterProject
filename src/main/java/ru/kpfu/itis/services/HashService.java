package ru.kpfu.itis.services;

import org.apache.commons.codec.digest.DigestUtils;

public class HashService {
    public static String hashPassword(String password) {
        String salt = "8@99&&##$";
        return  (DigestUtils.md5Hex(DigestUtils.md5Hex(password + salt) + salt)) + salt;
    }
}
