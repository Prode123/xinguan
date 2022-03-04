package com.example.humanresources.service;

import com.example.humanresources.entity.BizInStockInfo;
import com.github.pagehelper.PageInfo;


/**
 * (BizInStockInfo)表服务接口
 *
 * @author ZF
 */
public interface BizInStockInfoService {

    //通过ID查询
    BizInStockInfo selectById(Long id);

    //查询所有数据并分页
    PageInfo<BizInStockInfo> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(BizInStockInfo bizInStockInfo);

    //修改数据
    int updateById(BizInStockInfo bizInStockInfo);

}