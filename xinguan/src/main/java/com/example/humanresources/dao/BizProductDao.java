package com.example.humanresources.dao;

import com.example.humanresources.entity.BizProduct;
import com.example.humanresources.entity.BizProductStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (BizProduct)表数据库访问层
 *
 * @author ZF
 */

@Mapper
public interface BizProductDao {

    //通过ID查询
    BizProduct selectById(Long id);

    //模糊查询所有数据
    List<BizProduct> selectAll(BizProduct product);

    //模糊查询所有库存
    List<BizProduct> selectAllStock(BizProduct product);

    //新增数据
    int insert(BizProduct bizProduct);

    //修改数据
    int updateById(BizProduct bizProduct);

    //通过主键id删除数据
    int deleteById(Long id);

    // 查询入库明细
    List<BizProduct> selectProductInDetail(String inNum);

    // 查询出库明细
    List<BizProduct> selectProductOutDetail(String outNum);

    // 通过Id查询库存
    BizProductStock selectStockById(Long id);

}