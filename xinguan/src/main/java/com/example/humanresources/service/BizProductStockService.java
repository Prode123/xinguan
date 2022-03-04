package com.example.humanresources.service;

import com.example.humanresources.entity.BizProductStock;
import com.github.pagehelper.PageInfo;


/**
 * (BizProductStock)表服务接口
 *
 * @author ZF
 */
public interface BizProductStockService {

    //通过ID查询
    BizProductStock selectById(Long id);

    //查询所有数据并分页
    PageInfo<BizProductStock> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(BizProductStock bizProductStock);

    //修改数据
    int updateById(BizProductStock bizProductStock);

}