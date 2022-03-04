package com.example.humanresources.service.impl;

import com.example.humanresources.dao.BizInStockInfoDao;
import com.example.humanresources.entity.BizInStockInfo;
import com.example.humanresources.service.BizInStockInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (BizInStockInfo)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class BizInStockInfoServiceImpl implements BizInStockInfoService {
    @Autowired
    private BizInStockInfoDao bizInStockInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizInStockInfo selectById(Long id) {
        return bizInStockInfoDao.selectById(id);
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
        List<BizInStockInfo> dataList = bizInStockInfoDao.selectAll();
        PageInfo<BizInStockInfo> page = new PageInfo<BizInStockInfo>(dataList);
        return page;
    }

    /**
     * 新增数据
     *
     * @param bizInStockInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BizInStockInfo bizInStockInfo) {
        return bizInStockInfoDao.insert(bizInStockInfo);
    }

    /**
     * 修改数据
     *
     * @param bizInStockInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizInStockInfo bizInStockInfo) {
        return bizInStockInfoDao.updateById(bizInStockInfo);
    }

}