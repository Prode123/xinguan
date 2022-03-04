package com.example.humanresources.controller;

import com.example.humanresources.entity.TbRoleMenu;
import com.example.humanresources.service.TbRoleMenuService;
import com.example.humanresources.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色菜单关联表(TbRoleMenu)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:04
 */
@RestController
@RequestMapping("tbRoleMenu")
public class TbRoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TbRoleMenuService tbRoleMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("setMsg")
    public TbRoleMenu selectByPrimaryKey(int id) {
            return this.tbRoleMenuService.selectById(id);
    }

    @PostMapping("/authority/{id}")
    public R updateTbRoleMenu(long roleId, String[] menuName){
        int i = tbRoleMenuService.authorityByRoleId(roleId,menuName);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("授权成功");
        return r;
    }





}