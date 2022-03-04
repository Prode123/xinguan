package com.example.humanresources.dao;

import com.example.humanresources.entity.BizProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (BizProductCategory)表数据库访问层
 *
 * @author ZF
 */

@Mapper
public interface BizProductCategoryDao {

    //通过ID查询
    BizProductCategory selectById(Long id);

    //通过ID查询
//    List<BizProductCategory> selectByPid(Long pid);

    //查询所有数据
    List<BizProductCategory> selectAll();

    List<BizProductCategory> findALL();

    //新增数据
    int insert(BizProductCategory bizProductCategory);

    //修改数据
    int updateById(BizProductCategory bizProductCategory);

    //通过主键id删除数据
    int deleteById(Long id);


    /**
     * 通过pid进行查询 传入的为id
     * lt
     */
    List<BizProductCategory> selectByPid(Long pid);

    /**
     * 查询分类下有没有商品
     *
     * @param id
     * @return
     */
    int selectTwo(long id);
}