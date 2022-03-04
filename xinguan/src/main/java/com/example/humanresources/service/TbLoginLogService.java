package com.example.humanresources.service;

import com.example.humanresources.entity.TbLoginLog;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * 登录日志表(TbLoginLog)表服务接口
 * @author ZF
 */
public interface TbLoginLogService {

    //通过ID查询
    TbLoginLog selectById(Long id);

    //查询所有数据并分页
    PageInfo<TbLoginLog> selectAll(int pageNum, int pageSize);

    /**
     * 模糊查询所有数据并分页
     * @param pageNum
     * @param pageSize
     * @param tbLoginLog
     * @return
     */
    PageInfo<TbLoginLog> selectVagueAll(int pageNum, int pageSize,TbLoginLog tbLoginLog);

    //新增数据
    int insert(TbLoginLog tbLoginLog);

    //修改数据
    int updateById(TbLoginLog tbLoginLog);

    //通过主键id删除数据
    int deleteById(Long id);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int deleteMoreTbLoginLog(int[] id);

}