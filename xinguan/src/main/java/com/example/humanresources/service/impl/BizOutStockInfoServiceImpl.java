package com.example.humanresources.service.impl;

import com.example.humanresources.entity.BizOutStockInfo;
import com.example.humanresources.dao.BizOutStockInfoDao;
import com.example.humanresources.service.BizOutStockInfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BizOutStockInfo)表服务实现类
 * @author ZF
 */
@Service
@Transactional
public class BizOutStockInfoServiceImpl implements BizOutStockInfoService {
   @Autowired
    private BizOutStockInfoDao bizOutStockInfoDao;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizOutStockInfo selectById(Long id) {
        return bizOutStockInfoDao.selectById(id);
    }

    /**
     * 查询多条数据
     * @param pageNum 查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<BizOutStockInfo> dataList = bizOutStockInfoDao.selectAll();
        PageInfo<BizOutStockInfo> page = new PageInfo<BizOutStockInfo>(dataList);
        return page;
    }

    /**
     * 新增数据
     * @param bizOutStockInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BizOutStockInfo bizOutStockInfo) {
        return bizOutStockInfoDao.insert(bizOutStockInfo);
    }

    /**
     * 修改数据
     * @param bizOutStockInfo 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizOutStockInfo bizOutStockInfo) {
        return bizOutStockInfoDao.updateById(bizOutStockInfo);
    }

    /**
     * 通过主键id删除数据
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return bizOutStockInfoDao.deleteById(id);
    }
}