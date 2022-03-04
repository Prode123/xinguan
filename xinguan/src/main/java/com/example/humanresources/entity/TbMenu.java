package com.example.humanresources.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TbMenu implements Serializable {
    private static final long serialVersionUID = -58621586465125385L;
    /**
     * 菜单/按钮ID
     */

    private Long id;
    /**
     * 上级菜单ID
     */

    private Long parentId;
    /**
     * 菜单/按钮名称
     */

    private String menuName;
    /**
     * 菜单URL
     */

    private String url;
    /**
     * 权限标识
     */

    private String perms;
    /**
     * 图标
     */

    private String icon;
    /**
     * 类型 0菜单 1按钮
     */

    private String type;
    /**
     * 排序
     */

    private Long orderNum;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 修改时间
     */

    private Date modifiedTime;
    /**
     * 0：不可用，1：可用
     */

    private Integer available;
    /**
     * 0:不展开，1：展开
     */

    private Integer open;


    /**
     * 子菜单对象
     */
    private List<TbMenu> children;


}