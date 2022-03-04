package com.example.humanresources.utils;

public class OSSDowUtil {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    static String accessKeyId = "LTAI5tGedAMFSTNxC8Ns4JFb";
    static String accessKeySecret = "x948UJTCyREBlJAI68F4TWR5TYBXnw";
    // 填写Bucket名称。
    static String bucketName = "my-strive";
    // 填写Object的完整路径。Object完整路径中不能包含Bucket名称。
    static String objectName = "xinguan/Capture001.png";
}
