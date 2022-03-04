package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
@Data
public class BizOutStockInfo implements Serializable {
    private static final long serialVersionUID = 791976009145497926L;
    
    private Long id;
    
    private String outNum;
    
    private String pNum;
    
    private Integer productNumber;
    
    private Date createTime;
    
    private Date modifiedTime;



}