package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TbImage implements Serializable {
    private static final long serialVersionUID = 100028039079047471L;
    /**
    * 主键
    */
    
    private Long id;
    /**
    * 图片路径
    */
    
    private String path;
    /**
    * 图片大小
    */
    
    private Long size;
    /**
    * 图片类型
    */
    
    private String mediaType;
    /**
    * 图片后缀
    */
    
    private String suffix;
    /**
    * 图片高度
    */
    
    private Integer height;
    /**
    * 图片宽度
    */
    
    private Integer width;
    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;



}