package com.example.humanresources.service;

import com.example.humanresources.entity.BizProduct;
import com.example.humanresources.entity.Page;

import java.util.List;


/**
 * (BizProduct)表服务接口
 *
 * @author ZF
 */
public interface ProductService {

    //通过ID查询物资
    BizProduct selectById(Long id, int status);

    //新增物资数据
    int insert(BizProduct product);

    //修改物资数据
    int updateById(BizProduct product);

    //恢复物资数据
    int updateStatusByIdInt(Long id, int status);

    //通过主键id删除物资数据
    int deleteById(Long id);

    // 模糊查询物资库存列表
    Page<BizProduct> findProductStocks(int pageNum, int pageSize, BizProduct product);

    // 模糊查询物资所有库存
    List<BizProduct> findAllStocks(int pageNum, int pageSize, BizProduct product);

    // 模糊查询物资列表
    Page<BizProduct> findProductList(int pageNum, int pageSize, BizProduct product);

    // 审核通过
    int publish(Long id);
}