package com.example.humanresources.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbDepartmentResultMap implements Serializable {
    private static final long serialVersionUID = -32723474590818165L;
    
    private Long id;
    /**
    * 系名
    */
    
    private String name;
    /**
    * 系办公电话
    */
    
    private String phone;
    /**
    * 办公室地点
    */
    
    private String address;
    /**
    * 创建时间
    */
    
    private Date createTime;
    /**
    * 修改时间
    */
    
    private Date modifiedTime;



}