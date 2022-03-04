package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TbRole implements Serializable {
    private static final long serialVersionUID = -42932418777337492L;
    /**
    * 角色ID
    */
    
    private Long id;
    /**
    * 角色名称
    */
    
    private String roleName;
    /**
    * 角色描述
    */
    
    private String remark;
    /**
    * 创建时间
    */

    @DateTimeFormat(pattern = "YYYY-mm-DD HH-MM-SS")
    @JsonFormat(pattern = "YYYY-mm-DD HH-MM-SS")
    private Date createTime;
    /**
    * 修改时间
    */

    @DateTimeFormat(pattern = "YYYY-mm-DD HH-MM-SS")
    @JsonFormat(pattern = "YYYY-mm-DD HH-MM-SS")
    private Date modifiedTime;
    /**
    * 是否可用,0:不可用，1：可用
    */
    
    private Integer status;



}