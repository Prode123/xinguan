package com.example.humanresources.service;

import com.example.humanresources.entity.BizOutStockInfo;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * (BizOutStockInfo)表服务接口
 * @author ZF
 */
public interface BizOutStockInfoService {

    //通过ID查询
    BizOutStockInfo selectById(Long id);

    //查询所有数据并分页
    PageInfo<BizOutStockInfo> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(BizOutStockInfo bizOutStockInfo);

    //修改数据
    int updateById(BizOutStockInfo bizOutStockInfo);

    //通过主键id删除数据
    int deleteById(Long id);

}