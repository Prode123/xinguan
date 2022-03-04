package com.example.humanresources.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class BizProductCategory implements Serializable {
    private static final long serialVersionUID = 892831452437776529L;
    /**
     * 类别id
     */

    private Long id;
    /**
     * 类别名称
     */

    private String name;
    /**
     * 备注
     */

    private String remark;
    /**
     * 排序
     */

    private Integer sort;

    private Date createTime;

    private Date modifiedTime;
    /**
     * 父级分类id
     */

    private Long pid;

    //    @TableField
    private List<BizProductCategory> children;


}