package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.BizProductCategory;
import com.example.humanresources.entity.BizProductCategoryPlus;
import com.example.humanresources.service.BizProductCategoryService;
import com.example.humanresources.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (BizProductCategory)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("productCategory")
public class BizProductCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private BizProductCategoryService bizProductCategoryService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BizProductCategory selectByPrimaryKey(Long id) {
        return this.bizProductCategoryService.selectById(id);
    }

    /**
     * 添加分类
     *
     * @param bizProductCategory
     * @return
     * @author lt
     */
    @PostMapping("add")
    @Operation("添加分类")
    public R addCategory(@RequestBody BizProductCategory bizProductCategory) {
        R r = new R();
        System.out.println(bizProductCategory.getSort());
        if (bizProductCategory.getSort() == null) {
            r.setCode(201);
            r.setMsg("sort不能为空");
            r.setData(bizProductCategory);
        } else if (bizProductCategory.getPid() == null) {
            r.setCode(201);
            r.setMsg("pid不能为空");
            r.setData(bizProductCategory);
        } else {
            bizProductCategory.setCreateTime(new Date());
            bizProductCategory.setModifiedTime(new Date());
            int i = bizProductCategoryService.insert(bizProductCategory);
            if (i > 0) {
                if (i > 0) {
                    r.setCode(200);
                    r.setMsg("添加成功");
                    r.setData(bizProductCategory);
                } else {
                    r.setCode(201);
                    r.setMsg("添加失败");
                    r.setData(bizProductCategory);
                }
            }
        }
        return r;
    }

    /**
     * 分类树形结构
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @author lt
     */
    @GetMapping("categoryTree")
    public R categoryTree(int pageNum, int pageSize) {
        //  System.out.println(pageNum);
        BizProductCategoryPlus bizProductCategoryPlus = bizProductCategoryService.listWithTree(pageNum, pageSize);
//        BizProductCategoryPlus bizProductCategoryPlus=new BizProductCategoryPlus();
        return R.ok().setData(bizProductCategoryPlus);
    }

    /**
     * 删除分类
     *
     * @param bizProductCategory
     * @return
     */
    @DeleteMapping("delete")
    @Operation("删除分类")
    public R delete(BizProductCategory bizProductCategory) {
        R r = new R();
//        查找数据库有没有这条数据
        BizProductCategory bizProductCategory1 = bizProductCategoryService.selectById(bizProductCategory.getId());
//        如果有
        if (bizProductCategory1.getId() != null) {
//            比对pid是否包含此id
            List<BizProductCategory> bizProductCategories = bizProductCategoryService.selectByPid(bizProductCategory1.getId());
//            如果存在 说明存在子节点
//            如果不存在 再去查找有没有商品
            if (bizProductCategories.size() == 0) {
                int i = bizProductCategoryService.selectTwo(bizProductCategory.getId());
                if (i == 0) {
//                    可以删除
                    int i1 = bizProductCategoryService.deleteById(bizProductCategory.getId());
                    if (i1 == 1) {
                        r.setCode(200);
                        r.setMsg("删除成功");
                    } else {
                        r.setCode(201);
                        r.setMsg("删除失败");
                    }
                } else {
                    r.setData(201);
                    r.setMsg("该分类下存在商品引用，无法删除！");
                }
            }
        } else {
            r.setCode(201);
            r.setMsg("未查询到该数据，请检查id");
        }
        return r;
    }

    /**
     * 通过id查询回填
     *
     * @param bizProductCategory
     * @return
     */
    @GetMapping("edit")
    public R edit(BizProductCategory bizProductCategory) {
        R r = new R();
        BizProductCategory bizProductCategory1 = bizProductCategoryService.selectById(bizProductCategory.getId());
        if (bizProductCategory1.getSort() > 0) {
            r.setData(200);
            r.setMsg("查询成功");
            r.setData(bizProductCategory1);
        } else {
            r.setData(201);
            r.setMsg("查询失败");
            r.setData(bizProductCategory1);
        }
        return r;
    }

    /**
     * 所有分类
     *
     * @return
     */
    @GetMapping("findAll")
    public R findALl() {
        List<BizProductCategory> aLl = bizProductCategoryService.findALl();
        return R.ok().setData(aLl);
    }


    /**
     * 回填后修改更新分类
     *
     * @param bizProductCategory
     * @return
     */
    @PutMapping("update")
    @Operation("回填后修改更新分类")
    public R update(@RequestBody BizProductCategory bizProductCategory) {
        bizProductCategory.setModifiedTime(new Date());
        int i = bizProductCategoryService.updateById(bizProductCategory);
        if (i > 0) {
            return R.ok().setData(bizProductCategory);
        } else return R.fail();
    }

}