package com.example.humanresources.entity;

import java.io.Serializable;
import lombok.Data;
@Data
public class BizProductStock implements Serializable {
    private static final long serialVersionUID = 181554209445032480L;
    
    private Long id;
    
    private String pNum;
    /**
    * 商品库存结余
    */
    
    private Long stock;



}