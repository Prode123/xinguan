package com.example.humanresources.service;

import com.example.humanresources.entity.BizOutStock;
import com.example.humanresources.entity.BizOutStockDetail;
import com.example.humanresources.entity.Page;


/**
 * (BizOutStock)表服务接口
 *
 * @author ZF
 */
public interface BizOutStockService {

    //通过ID查询
    BizOutStock selectById(Long id);

    //查询所有数据并分页
    Page<BizOutStock> selectAll(int pageNum, int pageSize, BizOutStock outStock);

    //恢复物资数据
    int updateStatusById(Long id, int status);

    //新增数据
    int insert(BizOutStock bizOutStock);

    //修改数据
    int updateById(BizOutStock bizOutStock);

    //通过主键id删除数据
    int deleteById(Long id);

    // 通过Id查询出库明细
    BizOutStockDetail selectDetail(Long id, Integer pageNum);
}