package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.example.humanresources.service.TbMenuService;
import com.example.humanresources.service.TbUserService;
import com.example.humanresources.utils.ExportPoiUtil;
import com.example.humanresources.utils.JwtUtil;
import com.example.humanresources.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 菜单表(TbMenu)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:04
 */
@RestController
@RequestMapping("menu")
public class TbMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TbMenuService tbMenuService;
    @Resource
    private TbUserService tbUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbMenu selectByPrimaryKey(Long id) {
        return this.tbMenuService.selectById(id);
    }

    /**
     * 新增菜单
     */
    @Operation("新增菜单")
    @PostMapping("add")
    public R addMenu(@RequestBody TbMenu menuVO) {
        tbMenuService.insert(menuVO);
        return R.ok().setData("新增成功");
    }

    /**
     * 删除菜单
     */
    @Operation("删除菜单")
    @DeleteMapping("delete/{id}")
    public R deleteMenuNode(@PathVariable Long id) {
        tbMenuService.deleteById(id);
        return R.ok().setData("删除成功");
    }

    /**
     * 根据id编辑菜单，获取菜单详情,即回填
     */
    @GetMapping("edit/{id}")
    public R edit(@PathVariable Long id) {
        return R.ok().setData(tbMenuService.selectById(id));
    }

    /**
     * zf
     * 导出excel
     */
    @Operation("导出菜单列表")
    @GetMapping("excel")
    public void exportExcel(HttpServletResponse response, String username) {
        String fileName = "菜单列表";//excel文件名

        //从请求头中获取username
//        String authorization = request.getHeader("Authorization");
//        String username = JwtUtil.getUsername(authorization);

        //获取该用户所拥有的权限
        List<TbMenu> menu = tbUserService.findMenu(username);
        //列名
        String[] columnNames = {"编号", "父id", "菜单名称", "菜单url", "菜单图标", "是否展开", "菜单类型", "排序",
                "创建时间", "修改时间", "是否可用", "权限编码"};
        //将要创建的Map的键
        String[] keys = {"id", "parentId", "menuName", "url", "perms", "icon", "type", "orderNum",
                "createTime", "modifiedTime", "available", "open"};
        //生成并返回文件
        try {
            ExportPoiUtil.start_download(response, fileName, menu, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * zf
     * 用户登入后,根据角色加载菜单树
     */
    @GetMapping("findMenu")
    public R findRoleMenu(HttpServletRequest request) {
        //从请求头中获取username
        String authorization = request.getHeader("Authorization");
        String username = JwtUtil.getUsername(authorization);
        //通过用户名找到对应菜单并生成菜单树
        List<TbMenu> menu = tbUserService.findMenu(username);
        if (menu == null) {
            throw new ServiceException(ErrorCodeEnum.MENU_NOT_ERROR);
        }

        //返回菜单树
        return R.ok().setData(menu);
    }

    /**
     * 根据id更新菜单节点
     */
    @Operation("更新菜单节点")
    @PutMapping("update/{id}")
    public R updateById(@PathVariable Long id, @RequestBody TbMenu menuVO) {
        menuVO.setId(id);
        tbMenuService.updateById(menuVO);
        return R.ok().setData("更新成功");
    }

}