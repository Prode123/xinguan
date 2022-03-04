package com.example.humanresources.entity;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TbDepartment implements Serializable {
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

    /**
     * 部门人数
     */
    private int num;

    @DateTimeFormat(pattern = "YYYY-mm-DD HH-MM-SS")
    @JsonFormat(pattern = "YYYY-mm-DD HH-MM-SS")
    private Date createTime;
    /**
    * 修改时间
    */
    @DateTimeFormat(pattern = "YYYY-mm-DD HH-MM-SS")
    @JsonFormat(pattern = "YYYY-mm-DD HH-MM-SS")
    private Date modifiedTime;

}