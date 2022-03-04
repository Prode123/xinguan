package com.example.humanresources.dao;

import com.example.humanresources.entity.BizInStockInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (BizInStockInfo)表数据库访问层
 *
 * @author ZF
 */

@Mapper
public interface BizInStockInfoDao {

    //通过ID查询
    BizInStockInfo selectById(Long id);

    //查询所有数据
    List<BizInStockInfo> selectAll();


    //新增数据
    int insert(BizInStockInfo bizInStockInfo);

    //修改数据
    int updateById(BizInStockInfo bizInStockInfo);

    //通过主键id删除数据
    int deleteByInNum(String inNum);

}