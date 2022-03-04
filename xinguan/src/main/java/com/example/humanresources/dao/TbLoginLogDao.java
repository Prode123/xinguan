package com.example.humanresources.dao;

import com.example.humanresources.entity.TbLoginLog;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志表(TbLoginLog)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbLoginLogDao {

    //通过ID查询
    TbLoginLog selectById(Long id);

    //查询所有数据
    List<TbLoginLog> selectAll();

    /**
     * 模糊查询所有数据
     * @param tbLoginLog
     * @return
     */
    List<TbLoginLog> selectByTbLoginLog(TbLoginLog tbLoginLog);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int deleteMoreTbLoginLog(int[] id);


    //新增数据
    int insert(TbLoginLog tbLoginLog);

    //修改数据
    int updateById(TbLoginLog tbLoginLog);

    //通过主键id删除数据
    int deleteById(Long id);

}