package com.example.humanresources.dao;

import com.example.humanresources.entity.TbLog;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志表(TbLog)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbLogDao {

    //通过ID查询
    TbLog selectById(Long id);

    //查询所有数据
    List<TbLog> selectAll();


    //新增数据
    int insert(TbLog tbLog);

    //修改数据
    int updateById(TbLog tbLog);

    //通过主键id删除数据
    int deleteById(Long id);

    List<TbLog> selectByTbl(TbLog tbLog);

}