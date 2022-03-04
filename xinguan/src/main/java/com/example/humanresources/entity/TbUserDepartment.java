package com.example.humanresources.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;


@Data
public class TbUserDepartment implements Serializable {
    private static final long serialVersionUID = -32723474590818165L;
    /**
     * 用户ID
     */

    private Long id;
    /**
     * 用户名
     */

    private String username;

    private String nickname;
    /**
     * 邮箱
     */

    private String email;
    /**
     * 头像
     */

    private String avatar;
    /**
     * 联系电话
     */

    private String phoneNumber;
    /**
     * 状态 0锁定 1有效
     */

    private Integer status;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 修改时间
     */

    private Date modifiedTime;
    /**
     * 性别 0男 1女 2保密
     */

    private Integer sex;
    /**
     * 盐
     */

    private String salt;
    /**
     * 0:超级管理员，1：系统用户
     */

    private Integer type;
    /**
     * 密码
     */

    private String password;

    private Object birth;
    /**
     * 部门id
     */

    private Long departmentId;

    /**
     * 部门名字
     */
    private String departmentName;
}
