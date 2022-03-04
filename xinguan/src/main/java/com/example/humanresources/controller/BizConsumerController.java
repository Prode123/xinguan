package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.BizConsumer;
import com.example.humanresources.entity.BizSupplier;
import com.example.humanresources.service.BizConsumerService;
import com.example.humanresources.utils.R;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (BizConsumer)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("consumer")
public class BizConsumerController {
    /**
     * 服务对象
     */
    @Resource
    private BizConsumerService bizConsumerService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BizConsumer selectByPrimaryKey(Long id) {
        return this.bizConsumerService.selectById(id);
    }

    /**
     * 添加去向
     *
     * @param bizConsumer
     * @return
     */
    @PostMapping("add")
    @Operation("添加去向")
    public R add(@RequestBody BizConsumer bizConsumer) {
        R r = new R();
        bizConsumer.setCreateTime(new Date());
        int i = bizConsumerService.insert(bizConsumer);
        if (i > 0) {
            r.setCode(200);
            r.setMsg("添加成功");
            r.setData(bizConsumer);
        } else {
            r.setCode(201);
            r.setMsg("添加失败");
            r.setData(bizConsumer);
        }
        return r;
    }

    /**
     * 通过id删除去向
     *
     * @param bizConsumer
     * @return
     * @author lt
     */
    @DeleteMapping("delete")
    @Operation("通过id删除去向")
    public R deleteOneSupplierById(@RequestBody BizConsumer bizConsumer) {
        R r = new R();
        int i = bizConsumerService.deleteById(bizConsumer.getId());
        if (i == 1) {
            r.setCode(200);
            r.setMsg("删除成功");
            r.setData(bizConsumer);
        } else {
            r.setCode(201);
            r.setMsg("删除失败");
            r.setData(bizConsumer);
        }
        return r;
    }

    /**
     * 编辑时查询信息以回填
     *
     * @param bizConsumer
     * @return
     * @author lt
     */
    @GetMapping("edit")
    public R editOneSupplierById(BizConsumer bizConsumer) {
        R r = new R();
        BizConsumer bizConsumer1 = bizConsumerService.selectById(bizConsumer.getId());
        if (bizConsumer1 != null) {
            r.setCode(200);
            r.setMsg("查找成功");
            r.setData(bizConsumer1);
        } else {
            r.setCode(201);
            r.setMsg("查找失败");
            r.setData(bizConsumer1);
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
        PageInfo<BizConsumer> bizConsumerPageInfo = bizConsumerService.selectAll(pageNum, pageSize);
        if (bizConsumerPageInfo != null) {
            r.setCode(200);
            r.setMsg("查找成功");
            r.setData(bizConsumerPageInfo);
        } else {
            r.setCode(201);
            r.setMsg("查找失败");
            r.setData(bizConsumerPageInfo);
        }
        return r;
    }

    /**
     * 分页模糊查询
     *
     * @param pageNum
     * @param pageSize
     * @param bizConsumer
     * @return
     * @author lt
     */
    @GetMapping("findSupplierList")
    public R mohuSelect(BizConsumer bizConsumer, int pageNum, int pageSize) {
        R r = new R();
        PageInfo<BizSupplier> pageInfo = bizConsumerService.mohuSelect(pageNum, pageSize, bizConsumer);
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
     * @param bizConsumer
     * @return
     * @author lt
     */
    @GetMapping("update")
    @Operation("回填后更新")
    public R updateOneSupplierById(BizConsumer bizConsumer) {
        R r = new R();
        bizConsumer.setModifiedTime(new Date());
        int i = bizConsumerService.updateById(bizConsumer);
        if (i == 1) {
            r.setCode(200);
            r.setMsg("修改成功");
            r.setData(bizConsumerService.selectById(bizConsumer.getId()));
        } else {
            r.setCode(201);
            r.setMsg("修改失败");
            r.setData(bizConsumer);
        }
        return r;
    }

}