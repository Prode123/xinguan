package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class BizHealth implements Serializable {
    private static final long serialVersionUID = 270016018574829742L;
    
    private Long id;
    
    private String address;
    
    private Long userId;
    
    private Integer situation;
    
    private Integer touch;
    
    private Integer passby;
    
    private Integer reception;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



}