package com.example.humanresources.service;

import com.example.humanresources.entity.BizSupplier;
import com.github.pagehelper.PageInfo;


/**
 * 物资来源
 *
 * @author lt
 */
public interface BizSupplierService {

    //通过ID查询
    BizSupplier selectById(Long id);

    //查询所有数据并分页
    PageInfo<BizSupplier> selectAll(int pageNum, int pageSize);

    //模糊查询
    PageInfo<BizSupplier> mohuSelect(int pageNum, int pageSize, BizSupplier bizSupplier);

    /**
     * 新增数据 新增物资来源
     *
     * @param bizSupplier
     * @return
     * @author lt
     */
    int insert(BizSupplier bizSupplier);

    //修改数据
    int updateById(BizSupplier bizSupplier);

    //通过主键id删除数据
    int deleteById(Long id);

}