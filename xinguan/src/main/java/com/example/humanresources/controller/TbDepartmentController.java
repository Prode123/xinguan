package com.example.humanresources.controller;

import com.example.humanresources.entity.TbDepartment;
import com.example.humanresources.service.TbDepartmentService;
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
import java.util.List;

/**
 * (TbDepartment)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("/department")
@Api(tags = "系统部门管理", value = "系统部门CRUD操作")
public class TbDepartmentController {
    /**
     * 服务对象
     */
    @Resource
    private TbDepartmentService tbDepartmentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/edit/{id}")
    @ApiOperation(value = "编辑部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long")
    })
    public R selectByPrimaryKey(@PathVariable long id) {
        TbDepartment tbDepartment = tbDepartmentService.selectById(id);
        R r = new R();
        r.setCode(200);
        r.setData(tbDepartment);
        r.setMsg("查询成功");
        return r;
    }

    /**
     * 查询所有
     */
    @GetMapping("/findAll")
    @ApiOperation(value = "所有部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", type = "int"),
            @ApiImplicitParam(name = "pageSize", type = "int")
    })
    public R selectAll() {
        List<TbDepartment> tbDepartments = tbDepartmentService.selectAll();
        R r = new R();
        r.setCode(200);
        r.setData(tbDepartments);
        r.setMsg("查询成功");
        return r;
    }

    /**
     * 通过名字查询
     *
     * @param name
     * @return
     */
    @GetMapping("/findDepartmentList")
    @ApiOperation(value = "部门列表", notes = "部门列表，根据部门名模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", type = "String")
    })
    public R findByName(int pageNum, int pageSize, String name) {
        PageInfo pageInfo = tbDepartmentService.selectByName(pageNum, pageSize, name);
        R r = new R();
        r.setCode(200);
        r.setData(pageInfo);
        r.setMsg("查询成功");
        return r;
    }

    /**
     * 添加部门
     *
     * @param tbDepartment
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "TbDepartment", type = "body")
    })
    public R insert(@RequestBody TbDepartment tbDepartment) {
        int i = tbDepartmentService.insert(tbDepartment);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("添加成功");
        return r;
    }

    /**
     * 通过Id修改
     *
     * @param tbDepartment
     * @return
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value = "更新部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tbDepartment", type = "body")
    })
    public R updateById(@RequestBody TbDepartment tbDepartment) {
        int i = tbDepartmentService.updateById(tbDepartment);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("修改成功");
        return r;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long")
    })
    public R deleteById(@PathVariable long id) {
        int i = tbDepartmentService.deleteById(id);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("删除成功");
        return r;
    }

    @GetMapping("/excel")
    @ApiOperation(value = "导出excel", notes = "导出所有部门的excel表格")
    public void exportList(HttpServletResponse response, String ids) {
        List<TbDepartment> tbDepartments = tbDepartmentService.selectAllTbDepartment();
        String fileName = "系统部门表";
        // 列名
        String columnNames[] = {"ID", "办公电话", "部门名", "人数", "创建时间", "修改时间", "地址"};
        // map中的key
        String keys[] = {"id", "phone", "name", "num", "createTime", "modifiedTime"};
        try {
            ExportPOIUtils.start_download(response, fileName, tbDepartments, columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}