package com.example.humanresources.controller;

import com.example.humanresources.entity.TbLog;
import com.example.humanresources.service.TbLogService;
import com.example.humanresources.utils.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 操作日志表(TbLog)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("tbLog")
@Api(tags = "系统日志管理", value = "系统日志CRUD")
public class TbLogController {
    /**
     * 服务对象
     */
    @Resource
    private TbLogService tbLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbLog selectByPrimaryKey(Long id) {
        return this.tbLogService.selectById(id);
    }

    @DeleteMapping("/batchDelete/{ids}")
    @ApiOperation(value = "批量删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long")
    })
    public R batchDelete(@PathVariable long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            int j = tbLogService.deleteById(ids[i]);
        }
        R r = new R();
        r.setCode(200);
        r.setData(1);
        r.setMsg("删除成功");
        return r;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", type = "long")
    })
    public R delete(@PathVariable long id) {
        int i = tbLogService.deleteById(id);
        R r = new R();
        r.setCode(200);
        r.setData(i);
        r.setMsg("删除成功");
        return r;
    }

    @GetMapping("/findLogList")
    @ApiOperation(value = "日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tbLog", type = "body")
    })
    public R findLogList(int pageNum, int pageSize, TbLog tbLog) {
        PageInfo pageInfo = tbLogService.selectByTbl(pageNum, pageSize, tbLog);
        R r = new R();
        r.setCode(200);
        r.setData(pageInfo);
        r.setMsg("查询成功");
        return r;
    }


}