package com.example.humanresources.dao;

import com.example.humanresources.entity.BizConsumer;
import com.example.humanresources.entity.BizSupplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (BizConsumer)表数据库访问层
 *
 * @author ZF
 */

@Mapper
public interface BizConsumerDao {

    //通过ID查询
    BizConsumer selectById(Long id);

    //查询所有数据
    List<BizConsumer> selectAll();


    //新增数据
    int insert(BizConsumer bizConsumer);

    //修改数据
    int updateById(BizConsumer bizConsumer);

    //通过主键id删除数据
    int deleteById(Long id);

    //模糊查询
    List<BizSupplier> mohuSelect(BizConsumer bizConsumer);
}