package com.example.humanresources.dao;

import com.example.humanresources.entity.BizOutStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (BizOutStock)表数据库访问层
 *
 * @author ZF
 */

@Mapper
public interface BizOutStockDao {

    //通过ID查询
    BizOutStock selectById(Long id);

    //查询所有数据
    List<BizOutStock> selectAll();

    //新增数据
    int insert(BizOutStock bizOutStock);

    //修改数据
    int updateById(BizOutStock bizOutStock);

    //通过主键id删除数据
    int deleteById(Long id);

    //查询所有数据
    List<BizOutStock> selectAllByStatus(BizOutStock outStock);

}