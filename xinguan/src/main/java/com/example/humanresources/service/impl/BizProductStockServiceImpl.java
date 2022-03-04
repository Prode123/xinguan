package com.example.humanresources.service.impl;

import com.example.humanresources.dao.BizProductStockDao;
import com.example.humanresources.entity.BizProductStock;
import com.example.humanresources.service.BizProductStockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (BizProductStock)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class BizProductStockServiceImpl implements BizProductStockService {
    @Autowired
    private BizProductStockDao bizProductStockDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizProductStock selectById(Long id) {
        return bizProductStockDao.selectById(id);
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
        List<BizProductStock> dataList = bizProductStockDao.selectAll();
        PageInfo<BizProductStock> page = new PageInfo<BizProductStock>(dataList);
        return page;
    }

    /**
     * 新增数据
     *
     * @param bizProductStock 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BizProductStock bizProductStock) {
        return bizProductStockDao.insert(bizProductStock);
    }

    /**
     * 修改数据
     *
     * @param bizProductStock 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizProductStock bizProductStock) {
        return bizProductStockDao.updateById(bizProductStock);
    }

}