package com.example.humanresources.controller;

import com.example.humanresources.entity.TbUserRole;
import com.example.humanresources.service.TbUserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户角色关联表(TbUserRole)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:04
 */
@RestController
@RequestMapping("tbUserRole")
public class TbUserRoleController {
/**
 * 服务对象
 */
@Resource
private TbUserRoleService tbUserRoleService;

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("selectOne")
public TbUserRole selectByPrimaryKey(Long id) {
        return this.tbUserRoleService.selectById(id);
    }


}