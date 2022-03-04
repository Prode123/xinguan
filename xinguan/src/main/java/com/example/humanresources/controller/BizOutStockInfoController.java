package com.example.humanresources.controller;

import com.example.humanresources.entity.BizOutStockInfo;
import com.example.humanresources.service.BizOutStockInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BizOutStockInfo)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("bizOutStockInfo")
public class BizOutStockInfoController {
/**
 * 服务对象
 */
@Resource
private BizOutStockInfoService bizOutStockInfoService;

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("selectOne")
public BizOutStockInfo selectByPrimaryKey(Long id) {
        return this.bizOutStockInfoService.selectById(id);
    }


}