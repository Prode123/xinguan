package com.example.humanresources.dao;

import com.example.humanresources.entity.BizProductStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (BizProductStock)表数据库访问层
 *
 * @author ZF
 */

@Mapper
public interface BizProductStockDao {

    //通过ID查询
    BizProductStock selectById(Long id);

    //查询所有数据
    List<BizProductStock> selectAll();


    //新增数据
    int insert(BizProductStock bizProductStock);

    //修改数据
    int updateById(BizProductStock bizProductStock);

    // 通过p_num删除数据
    int deleteByPNum(String PNum);

    // 通过p_num查询数据
    BizProductStock selectByPNum(String PNum);


}