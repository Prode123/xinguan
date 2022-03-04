package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.BizHealth;
import com.example.humanresources.service.BizHealthService;
import com.example.humanresources.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BizHealth)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("health")
public class BizHealthController {
    /**
     * 服务对象
     */
    @Resource
    private BizHealthService bizHealthService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BizHealth selectByPrimaryKey(Long id) {
        return this.bizHealthService.selectById(id);
    }

    /**
     * 健康上报
     *
     * @param bizHealth
     * @return
     */
    @PostMapping("report")
    @Operation("健康上报")
    public R insertBizHealth(@RequestBody BizHealth bizHealth) {
        return R.ok().setData(this.bizHealthService.insert(bizHealth));
    }

    /**
     * 查询该用户的所有健康记录
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("history")
    public R selectByUserId(int pageNum, int pageSize) {
        return R.ok().setData(this.bizHealthService.selectPageInfoByUserId(pageNum, pageSize));
    }

    /**
     * 查询是否上报
     *
     * @return
     */
    @GetMapping("isReport")
    public R selectByUserIdIsReport() {
        return R.ok().setData(this.bizHealthService.selectByUserIdIsReport());
    }


}