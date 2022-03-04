package com.example.humanresources.dao;

import com.example.humanresources.entity.BizInStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (BizInStock)表数据库访问层
 *
 * @author ZF
 */

@Mapper
public interface BizInStockDao {

    //通过ID查询
    BizInStock selectById(Long id);

    //查询所有数据
    List<BizInStock> selectAllByStatus(BizInStock inStock);


    //新增数据
    int insert(BizInStock bizInStock);

    //修改数据
    int updateById(BizInStock bizInStock);

    //通过主键id删除数据
    int deleteById(Long id);

}