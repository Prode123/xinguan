package com.example.humanresources.dao;

import com.example.humanresources.entity.BizSupplier;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物资来源
 * @author lt
 */

@Mapper
public interface BizSupplierDao {

    //通过ID查询
    BizSupplier selectById(Long id);

    //查询所有数据
    List<BizSupplier> selectAll();


    /**
     * 新增数据 新增物资来源
     * @param bizSupplier
     * @return
     * @author lt
     */
    int insert(BizSupplier bizSupplier);

    //修改数据
    int updateById(BizSupplier bizSupplier);

    //通过主键id删除数据
    int deleteById(Long id);


    //模糊查询
    List<BizSupplier> mohuSelect(BizSupplier bizSupplier);
}