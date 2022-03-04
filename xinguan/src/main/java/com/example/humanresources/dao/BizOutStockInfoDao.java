package com.example.humanresources.dao;

import com.example.humanresources.entity.BizOutStockInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (BizOutStockInfo)表数据库访问层
 *
 * @author ZF
 */

@Mapper
public interface BizOutStockInfoDao {

    //通过ID查询
    BizOutStockInfo selectById(Long id);

    //查询所有数据
    List<BizOutStockInfo> selectAll();


    //新增数据
    int insert(BizOutStockInfo bizOutStockInfo);

    //修改数据
    int updateById(BizOutStockInfo bizOutStockInfo);

    //通过主键id删除数据
    int deleteById(Long id);

    int deleteByOutNum(String outNum);
}