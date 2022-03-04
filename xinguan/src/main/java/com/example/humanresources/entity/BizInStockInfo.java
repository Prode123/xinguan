package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
@Data
public class BizInStockInfo implements Serializable {
    private static final long serialVersionUID = 619439255243262870L;
    
    private Long id;
    /**
    * 入库单编号
    */
    
    private String inNum;
    /**
    * 商品编号
    */
    
    private String pNum;
    /**
    * 数量
    */
    
    private Integer productNumber;
    
    private Date createTime;
    
    private Date modifiedTime;



}