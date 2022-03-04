package com.example.humanresources.service;

import com.example.humanresources.entity.BizInStock;
import com.example.humanresources.entity.BizInStockDetail;
import com.example.humanresources.entity.Page;


/**
 * (BizInStock)表服务接口
 *
 * @author ZF
 */
public interface BizInStockService {

    //通过ID查询
    BizInStock selectById(Long id);

    //查询所有数据并分页
    Page<BizInStock> selectAll(int pageNum, int pageSize, BizInStock inStock);

    //恢复物资数据
    int updateStatusById(Long id, int status);

    //新增数据
    int insert(BizInStock bizInStock);

    //修改数据
    int updateById(BizInStock bizInStock);

    //通过主键id删除数据
    int deleteById(Long id);

    // 查询入库明细
    BizInStockDetail selectDetail(Long id, Integer pageNum);

    // 审核通过
    int publish(Long id);
}