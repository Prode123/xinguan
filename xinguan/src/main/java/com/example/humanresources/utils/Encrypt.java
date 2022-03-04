package com.example.humanresources.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Encrypt {
    public static String getEncryption(String password, String salt) {
        SimpleHash simpleHash = new SimpleHash("md5", password, salt, 2);
        return simpleHash.toString();
    }
}
