package com.example.humanresources.service.impl;

import com.example.humanresources.entity.BizProductCategory;
import com.example.humanresources.entity.BizSupplier;
import com.example.humanresources.dao.BizSupplierDao;
import com.example.humanresources.entity.BizSupplier;
import com.example.humanresources.service.BizSupplierService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 物资来源
 * @author lt
 */
@Service
@Transactional
public class BizSupplierServiceImpl implements BizSupplierService {
    @Autowired
    private BizSupplierDao bizSupplierDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizSupplier selectById(Long id) {
        return bizSupplierDao.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param pageNum  查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BizSupplier> dataList = bizSupplierDao.selectAll();
        PageInfo<BizSupplier> page = new PageInfo<BizSupplier>(dataList);
        return page;
    }

    @Override
    public PageInfo mohuSelect(int pageNum, int pageSize, BizSupplier bizSupplier) {
        PageHelper.startPage(pageNum, pageSize);
        List<BizSupplier> dataList = bizSupplierDao.mohuSelect(bizSupplier);
        PageInfo<BizSupplier> page = new PageInfo<BizSupplier>(dataList);
        return page;
    }

    /**
     * 新增数据 新增物资来源
     * @param bizSupplier
     * @return
     * @author lt
     */
    @Override
    public int insert(BizSupplier bizSupplier) {
        return  bizSupplierDao.insert(bizSupplier);
    }


    /**
     * 修改数据
     *
     * @param bizSupplier 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizSupplier bizSupplier) {
        return bizSupplierDao.updateById(bizSupplier);
    }

    /**
     * 通过主键id删除数据
     *
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return bizSupplierDao.deleteById(id);
    }
}