package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TbLoginLog implements Serializable {
    private static final long serialVersionUID = 263819690359097238L;
    /**
    * id
    */
    
    private Long id;
    /**
    * 用户名
    */
    
    private String username;
    /**
    * 登录时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    /**
    * 登录地点
    */
    
    private String location;
    /**
    * IP地址
    */
    
    private String ip;
    /**
    * 操作系统
    */
    
    private String userSystem;
    /**
    * 浏览器
    */
    
    private String userBrowser;



}