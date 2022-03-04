package com.example.humanresources.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

/**
 * 封装base64和16进制编码解码工具类
 */
public class EncodesUtil {
    /*
    16进制数组转换为字符串
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeToString(input);
    }

    /*
    字符串转为16进制数组
     */
    public static byte[] decodeHex(String input) {
        return Hex.decode(input);
    }

    /*
    base64数组转换为字符串
     */
    public static String encodeBase64(byte[] input) {
        return Base64.encodeToString(input);
    }

    /*
    base64字符串转换为数组
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decode(input);
    }
}
