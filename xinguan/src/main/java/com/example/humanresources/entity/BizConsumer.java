package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
@Data
public class BizConsumer implements Serializable {
    private static final long serialVersionUID = -72820396230249695L;
    
    private Long id;
    /**
    * 物资消费方
    */
    
    private String name;
    /**
    * 地址
    */
    
    private String address;
    
    private Date createTime;
    
    private Date modifiedTime;
    /**
    * 联系电话
    */
    
    private String phone;
    
    private Integer sort;
    /**
    * 联系人姓名
    */
    
    private String contact;



}