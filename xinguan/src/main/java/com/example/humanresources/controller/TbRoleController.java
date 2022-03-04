package com.example.humanresources.controller;

import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.entity.TbRole;
import com.example.humanresources.entity.TbRoleExcel;
import com.example.humanresources.entity.TbRoleMenu;
import com.example.humanresources.service.TbMenuService;
import com.example.humanresources.service.TbRoleMenuService;
import com.example.humanresources.service.TbRoleService;
import com.example.humanresources.utils.ExportPOIUtils;
import com.example.humanresources.utils.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色表(TbRole)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:04
 */
@RestController
@RequestMapping("tbRole")
@Api(tags = "系统角色管理", value = "系统角色CRUD操作")
public class TbRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TbRoleService tbRoleService;

    @Resource
    private TbRoleMenuService tbRoleMenuService;

    @Resource
    private TbMenuService tbMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/edit/{id}")
    @ApiOperation(value = "编辑用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long")
    })
    public R selectByPrimaryKey(@PathVariable Long id) {
        TbRole tbRole = this.tbRoleService.selectById(id);
        R r = new R();
        r.setCode(200);
        r.setData(tbRole);
        r.setMsg("查询成功");
        return r;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tbRole", type = "body")
    })
    public R insert(@RequestBody TbRole tbRole) {
        int i = tbRoleService.insert(tbRole);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("新建成功");
        return r;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long")
    })
    public R delete(@PathVariable long id) {
        int i = tbRoleService.deleteById(id);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("删除成功");
        return r;
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tbRole", type = "body")
    })
    public R edit(@RequestBody TbRole tbRole, @PathVariable long id) {
        tbRole.setId(id);
        int i = tbRoleService.updateById(tbRole);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("修改成功");
        return r;
    }

    @GetMapping("/excel")
    @ApiOperation(value = "导出excel")
    public void exportList(HttpServletResponse response, String ids) {
        List<TbRoleExcel> tbRoleExcels = tbRoleService.selectAll();
        String fileName = "系统角色表";
        // 列名
        String columnNames[] = {"ID", "角色名", "创建时间", "是否禁用", "备注"};
        // map中的key
        String keys[] = {"id", "roleName", "createTime", "status", "remark"};
        try {
            ExportPOIUtils.start_download(response, fileName, tbRoleExcels, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/selectByRoleName")
    @ApiOperation(value = "角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", type = "String")
    })
    public R selectByRoleName(int pageNum, int pageSize, String roleName) {
        PageInfo tbRoles = tbRoleService.selectByRoleName(pageNum, pageSize, roleName);
        R r = new R();
        r.setCode(200);
        r.setData(tbRoles);
        r.setMsg("查询成功");
        return r;
    }

    @PutMapping("/updateStatus/{id}/{status}")
    @ApiOperation(value = "更新状态", notes = "禁用和更新两种状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long"),
            @ApiImplicitParam(name = "status", type = "int")
    })
    public R updateById(@PathVariable long id, @PathVariable int status) {
        TbRole tbRole = new TbRole();
        tbRole.setId(id);
        tbRole.setStatus(status);
        int i = tbRoleService.updateById(tbRole);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("修改成功");
        return r;
    }

    @PostMapping("/authority/{id}")
    @ApiOperation(value = "角色授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long"),
            @ApiImplicitParam(name = "menuName", type = "String")
    })
    public R updateTbRoleMenu(@PathVariable("id") long roleId, String[] menuName) {
        int i = tbRoleMenuService.authorityByRoleId(roleId, menuName);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("授权成功");
        return r;
    }

    @GetMapping("/findRoleMenu/{id}")
    @ApiOperation(value = "角色菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long")
    })
    public R findRoleMenu(@PathVariable("id") long roleId) {
        List<String> roleNames = new ArrayList<>();
        List<TbRoleMenu> tbRoleMenus = tbRoleMenuService.selectByRoleId(roleId);
        for (TbRoleMenu tbRoleMenu : tbRoleMenus) {
            TbMenu tbMenu = tbMenuService.selectById(tbRoleMenu.getMenuId());
            roleNames.add(tbMenu.getMenuName());
        }
        R r = new R();
        r.setCode(200);
        r.setData(roleNames);
        r.setMsg("查询成功");
        return r;
    }

}