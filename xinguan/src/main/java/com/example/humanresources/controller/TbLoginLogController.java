package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.TbLoginLog;
import com.example.humanresources.service.TbLoginLogService;
import com.example.humanresources.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录日志表(TbLoginLog)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("loginLog")
public class TbLoginLogController {
    /**
     * 服务对象
     */
    @Resource
    private TbLoginLogService tbLoginLogService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbLoginLog selectByPrimaryKey(Long id) {
        return this.tbLoginLogService.selectById(id);
    }

    /**
     * 模糊查询所有数据并分页
     *
     * @param pageNum
     * @param pageSize
     * @param tbLoginLog
     * @return
     */
    @GetMapping("findLoginLogList")
    public R selectVagueAll(TbLoginLog tbLoginLog, int pageNum, int pageSize) {
        return R.ok().setData(tbLoginLogService.selectVagueAll(pageNum, pageSize, tbLoginLog));
    }

    /**
     * 删除单个数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    @Operation("删除日志")
    public R deleteById(@PathVariable Long id) {
        return R.ok().setData(tbLoginLogService.deleteById(id));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("batchDelete/{ids}")
    @Operation("批量删除")
    public R deleteMoreTbLoginLog(@PathVariable int[] ids) {
        return R.ok().setData(tbLoginLogService.deleteMoreTbLoginLog(ids));
    }

    /**
     * 用户登入报表
     *
     * @param tbLoginLog
     * @return
     */
    @PostMapping("loginReport")
    public R insertTbLoginLog(@RequestBody TbLoginLog tbLoginLog) {
        return R.ok().setData(tbLoginLogService.insert(tbLoginLog));
    }

}