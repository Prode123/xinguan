package com.example.humanresources.service;

import com.example.humanresources.entity.BizConsumer;
import com.example.humanresources.entity.BizSupplier;
import com.github.pagehelper.PageInfo;


/**
 * (BizConsumer)表服务接口
 *
 * @author ZF
 */
public interface BizConsumerService {

    //通过ID查询
    BizConsumer selectById(Long id);

    //查询所有数据并分页
    PageInfo<BizConsumer> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(BizConsumer bizConsumer);

    //修改数据
    int updateById(BizConsumer bizConsumer);

    //通过主键id删除数据
    int deleteById(Long id);

    //模糊查询
    PageInfo<BizSupplier> mohuSelect(int pageNum, int pageSize, BizConsumer bizConsumer);

}