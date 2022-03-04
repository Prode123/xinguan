package com.example.humanresources.entity;

import java.io.Serializable;
import lombok.Data;
@Data
public class TbRoleMenu implements Serializable {
    private static final long serialVersionUID = -27743665264746534L;
    
    private Integer id;
    /**
    * 角色ID
    */
    
    private Long roleId;
    /**
    * 菜单/按钮ID
    */
    
    private Long menuId;



}