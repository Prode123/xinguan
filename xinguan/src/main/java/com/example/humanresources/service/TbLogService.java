package com.example.humanresources.service;

import com.example.humanresources.entity.TbLog;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * 操作日志表(TbLog)表服务接口
 * @author ZF
 */
public interface TbLogService {

    //通过ID查询
    TbLog selectById(Long id);

    //查询所有数据并分页
    PageInfo<TbLog> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(TbLog tbLog);

    //修改数据
    int updateById(TbLog tbLog);

    //通过主键id删除数据
    int deleteById(Long id);

    PageInfo selectByTbl(int pageNum,int pageSize,TbLog tbLog);

}