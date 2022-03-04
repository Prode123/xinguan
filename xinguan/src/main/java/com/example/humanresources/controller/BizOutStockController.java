package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.BizOutStock;
import com.example.humanresources.service.BizOutStockService;
import com.example.humanresources.utils.JwtUtil;
import com.example.humanresources.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (BizOutStock)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("outStock")
public class BizOutStockController {
    /**
     * 服务对象
     */
    @Resource
    private BizOutStockService bizOutStockService;

    // 新增出库
    @PostMapping("/addOutStock")
    @Operation("新增出库单")
    public R addOutStock(@RequestBody BizOutStock outStock, HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        outStock.setOperator(JwtUtil.getUsername(token));

        return R.ok().setData(bizOutStockService.insert(outStock));
    }

    // 恢复出库信息
    @PutMapping("/back/{id}")
    @Operation("恢复入库单")
    public R back(@PathVariable Long id) {
        return R.ok().setData(bizOutStockService.updateStatusById(id, 0));
    }

    // 删除出库信息
    @DeleteMapping("/delete/{id}")
    @Operation("删除出库单")
    public R delete(@PathVariable Long id) {
        return R.ok().setData(bizOutStockService.deleteById(id));
    }

    // 查询出库明细
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable Long id, Integer pageNum) {
        return R.ok().setData(bizOutStockService.selectDetail(id, pageNum));
    }

    // 查询出库列表
    @GetMapping("/findOutStockList")
    public R findInStockList(Integer pageNum, Integer pageSize, BizOutStock outStock) {
        return R.ok().setData(bizOutStockService.selectAll(pageNum, pageSize, outStock));
    }

    // 审核通过
    @PutMapping("/publish/{id}")
    @Operation("通过出库单审核")
    public R publish(@PathVariable Long id) {
        return R.ok().setData(bizOutStockService.updateStatusById(id, 0));
    }


    // 移入回收站
    @PutMapping("/remove/{id}")
    @Operation("移除出库单")
    public R remove(@PathVariable Long id) {
        return R.ok().setData(bizOutStockService.updateStatusById(id, 1));
    }

}