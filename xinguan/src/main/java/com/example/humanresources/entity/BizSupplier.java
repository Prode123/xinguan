package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 物资来源实体类
 * @author lt
 */
@Data
public class BizSupplier implements Serializable {
    private static final long serialVersionUID = -25325648517399928L;
    
    private Long id;
    /**
    * 供应商名称
    */
    
    private String name;
    /**
    * 供应商地址
    */
    
    private String address;
    /**
    * 供应商邮箱
    */
    
    private String email;
    /**
    * 供应商电话
    */
    
    private String phone;
    
    private Date createTime;
    
    private Date modifiedTime;
    /**
    * 排序
    */
    
    private Integer sort;
    /**
    * 联系人
    */
    
    private String contact;



}