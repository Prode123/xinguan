package com.example.humanresources.utils;

import java.util.UUID;

/**
 * @author zpy  on  2021/12/06
 */
public class UUIDUitl {

    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
