package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.BizProduct;
import com.example.humanresources.service.ProductService;
import com.example.humanresources.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BizProduct)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * 添加物资
     */
    @PostMapping("/add")
    @Operation("添加物资")
    public R addProduct(@RequestBody BizProduct product) {
        return R.ok().setData(productService.insert(product));
    }

    /**
     * 恢复物资
     */
    @PutMapping("/back/{id}")
    @Operation("恢复物资")
    public R backProduct(@PathVariable Long id) {
        return R.ok().setData(productService.updateStatusByIdInt(id, 0));
    }

    /**
     * 删除物资
     */
    @DeleteMapping("/delete/{id}")
    @Operation("删除物资")
    public R deleteProdict(@PathVariable Long id) {
        return R.ok().setData(productService.deleteById(id));
    }

    /**
     * 编辑物资
     */
    @GetMapping("/edit/{id}")
    public R getEdit(@PathVariable Long id) {
        return R.ok().setData(productService.selectById(id, 0));
    }

    /**
     * 获取全部库存
     */
    @GetMapping("/findAllStocks")
    public R findAllStocks(int pageNum, int pageSize, BizProduct product) {
        return R.ok().setData(productService.findAllStocks(pageNum, pageSize, product));
    }


    /**
     * 获取全部物资信息
     */
    @GetMapping("/findProductList")
    public R findProductList(int pageNum, int pageSize, BizProduct product) {
        return R.ok().setData(productService.findProductList(pageNum, pageSize, product));
    }

    /**
     * 获取库存列表
     */
    @GetMapping("/findProductStocks")
    public R findProductStocks(int pageNum, int pageSize, BizProduct product) {
        return R.ok().setData(productService.findProductStocks(pageNum, pageSize, product));
    }


    /**
     * 可入库库存列表
     */
    @GetMapping("/findProducts")
    public R findProducts(int pageNum, int pageSize, BizProduct product) {
        return R.ok().setData(productService.findProductList(pageNum, pageSize, product));
    }

    /**
     * 通过审核物资
     */
    @PutMapping("/publish/{id}")
    @Operation("通过物资审核")
    public R publish(@PathVariable Long id) {
        return R.ok().setData(productService.publish(id));
    }


    /**
     * 移入回收站
     */
    @PutMapping("/remove/{id}")
    @Operation("移除物资")
    public R remove(@PathVariable Long id) {
        return R.ok().setData(productService.updateStatusByIdInt(id, 1));
    }

    /**
     * 更新物资信息
     */
    @PutMapping("/update/{id}")
    @Operation("更新物资")
    public R update(@PathVariable Long id, @RequestBody BizProduct product) {
        product.setId(id);
        return R.ok().setData(productService.updateById(product));
    }
}