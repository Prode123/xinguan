package com.example.humanresources.entity;

import java.io.Serializable;
import lombok.Data;
@Data
public class TbUserRole implements Serializable {
    private static final long serialVersionUID = 786008042855449502L;
    
    private Integer id;
    /**
    * 用户ID
    */
    
    private Long userId;
    /**
    * 角色ID
    */
    
    private Long roleId;



}