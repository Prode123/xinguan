package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.BizSupplier;
import com.example.humanresources.service.BizSupplierService;
import com.example.humanresources.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 物资来源
 *
 * @author lt
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("supplier")
public class BizSupplierController {
    /**
     * 服务对象
     */
    @Resource
    private BizSupplierService bizSupplierService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BizSupplier selectByPrimaryKey(Long id) {
        return this.bizSupplierService.selectById(id);
    }

    /**
     * 新增数据 新增物资来源
     *
     * @param bizSupplier
     * @return R
     * @author lt
     */
    @PostMapping("add")
    @Operation("新增数据 新增物资来源")
    public R addOneSupplier(BizSupplier bizSupplier) {
        R r = new R();
        bizSupplier.setCreateTime(new Date());
        int i = bizSupplierService.insert(bizSupplier);
        if (i > 0) {
            r.setCode(200);
            r.setMsg("添加成功");
            r.setData(bizSupplier);
        } else {
            r.setCode(201);
            r.setMsg("添加失败");
            r.setData(bizSupplier);
        }
        return r;
    }

    /**
     * 通过id删除物资来源
     *
     * @param bizSupplier
     * @return
     * @author lt
     */
    @DeleteMapping("delete")
    @Operation("通过id删除物资来源")
    public R deleteOneSupplierById(BizSupplier bizSupplier) {
        R r = new R();
        int i = bizSupplierService.deleteById(bizSupplier.getId());
        if (i == 1) {
            r.setCode(200);
            r.setMsg("删除成功");
            r.setData(bizSupplier);
        } else {
            r.setCode(201);
            r.setMsg("删除失败");
            r.setData(bizSupplier);
        }
        return r;
    }

    /**
     * 编辑时查询信息以回填
     *
     * @param bizSupplier
     * @return
     * @author lt
     */
    @GetMapping("edit")
    public R editOneSupplierById(BizSupplier bizSupplier) {
        R r = new R();
        BizSupplier bizSupplier1 = bizSupplierService.selectById(bizSupplier.getId());
        if (bizSupplier1 != null) {
            r.setCode(200);
            r.setMsg("查找成功");
            r.setData(bizSupplier1);
        } else {
            r.setCode(201);
            r.setMsg("查找失败");
            r.setData(bizSupplier1);
        }
        return r;
    }

    /**
     * 分页查询所有
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @author lt
     */
    @GetMapping("findAll")
    public R findAllSupplier(int pageNum, int pageSize) {
        R r = new R();
        PageInfo<BizSupplier> pageInfo = bizSupplierService.selectAll(pageNum, pageSize);
        if (pageInfo != null) {
            r.setCode(200);
            r.setMsg("查找成功");
            r.setData(pageInfo);
        } else {
            r.setCode(201);
            r.setMsg("查找失败");
            r.setData(pageInfo);
        }
        return r;
    }

    /**
     * 分页模糊查询
     *
     * @param pageNum
     * @param pageSize
     * @param bizSupplier
     * @return
     * @author lt
     */
    @GetMapping("findSupplierList")
    public R mohuSelect(int pageNum, int pageSize, BizSupplier bizSupplier) {
        R r = new R();
        PageInfo<BizSupplier> pageInfo = bizSupplierService.mohuSelect(pageNum, pageSize, bizSupplier);
        if (pageInfo != null) {
            r.setCode(200);
            r.setMsg("查找成功");
            r.setData(pageInfo);
        } else {
            r.setCode(201);
            r.setMsg("查找失败");
            r.setData(pageInfo);
        }
        return r;
    }

    /**
     * 回填后更新
     *
     * @param bizSupplier
     * @return
     * @author lt
     */
    @GetMapping("update")
    @Operation("回填后更新")
    public R updateOneSupplierById(BizSupplier bizSupplier) {
        R r = new R();
        bizSupplier.setModifiedTime(new Date());
        int i = bizSupplierService.updateById(bizSupplier);
        if (i == 1) {
            r.setCode(200);
            r.setMsg("修改成功");
            r.setData(bizSupplier);
        } else {
            r.setCode(201);
            r.setMsg("修改失败");
            r.setData(bizSupplier);
        }
        return r;
    }
}