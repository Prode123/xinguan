package com.example.humanresources.controller;

import com.example.humanresources.entity.BizInStockInfo;
import com.example.humanresources.service.BizInStockInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BizInStockInfo)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("bizInStockInfo")
public class BizInStockInfoController {
/**
 * 服务对象
 */
@Resource
private BizInStockInfoService bizInStockInfoService;

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */
@GetMapping("selectOne")
public BizInStockInfo selectByPrimaryKey(Long id) {
        return this.bizInStockInfoService.selectById(id);
    }


}