package com.example.humanresources.service;

import com.example.humanresources.entity.BizProductCategory;
import com.example.humanresources.entity.BizProductCategoryPlus;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * (BizProductCategory)表服务接口
 *
 * @author ZF
 */
public interface BizProductCategoryService {

    //通过ID查询
    BizProductCategory selectById(Long id);

    //查询所有数据并分页
    PageInfo<BizProductCategory> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(BizProductCategory bizProductCategory);

    //修改数据
    int updateById(BizProductCategory bizProductCategory);

    //通过主键id删除数据
    int deleteById(Long id);

    //树形查询
    BizProductCategoryPlus listWithTree(int pageNum, int pageSize);

    //所有分类
    List<BizProductCategory> findALl();

    /**
     * 查询所有数据 不进行分页
     */
//    List<BizProductCategory>

    /**
     * 通过pid进行查询 传入的为id
     * lt
     */
    List<BizProductCategory> selectByPid(Long pid);

//    List<BizProductCategory> listWithTree();

    /**
     * 查询分类下有没有商品
     *
     * @param id
     * @return
     */
    int selectTwo(long id);


}