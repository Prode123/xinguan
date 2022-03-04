package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.BizInStock;
import com.example.humanresources.service.BizInStockService;
import com.example.humanresources.utils.JwtUtil;
import com.example.humanresources.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (BizInStock)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("inStock")
@Slf4j
public class BizInStockController {
    /**
     * 服务对象
     */
    @Resource
    private BizInStockService bizInStockService;

    // 新增入库
    @PostMapping("/addIntoStock")
    @Operation("新增入库单")
    public R addIntoStock(@RequestBody BizInStock inStock, HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        inStock.setOperator(JwtUtil.getUsername(token));

        return R.ok().setData(bizInStockService.insert(inStock));
    }

    // 恢复入库信息
    @PutMapping("/back/{id}")
    @Operation("恢复入库单")
    public R back(@PathVariable Long id) {
        return R.ok().setData(bizInStockService.updateStatusById(id, 0));
    }

    // 删除入库信息
    @DeleteMapping("/delete/{id}")
    @Operation("删除入库单")
    public R delete(@PathVariable Long id) {
        return R.ok().setData(bizInStockService.deleteById(id));
    }

    // 查询入库明细
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable Long id, Integer pageNum) {
        return R.ok().setData(bizInStockService.selectDetail(id, pageNum));
    }

    // 查询入库列表
    @GetMapping("/findInStockList")
    public R findInStockList(Integer pageNum, Integer pageSize, BizInStock inStock) {
        return R.ok().setData(bizInStockService.selectAll(pageNum, pageSize, inStock));
    }

    // 审核通过
    @PutMapping("/publish/{id}")
    @Operation("通过入库单审核")
    public R publish(@PathVariable Long id) {
        return R.ok().setData(bizInStockService.publish(id));
    }

    // 移入回收站
    @PutMapping("/remove/{id}")
    @Operation("移除入库单")
    public R remove(@PathVariable Long id) {
        return R.ok().setData(bizInStockService.updateStatusById(id, 1));
    }
}