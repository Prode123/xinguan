package com.example.humanresources.service.impl;

import com.example.humanresources.dao.BizConsumerDao;
import com.example.humanresources.entity.BizConsumer;
import com.example.humanresources.entity.BizSupplier;
import com.example.humanresources.service.BizConsumerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (BizConsumer)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class BizConsumerServiceImpl implements BizConsumerService {
    @Autowired
    private BizConsumerDao bizConsumerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizConsumer selectById(Long id) {
        return bizConsumerDao.selectById(id);
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
        List<BizConsumer> dataList = bizConsumerDao.selectAll();
        PageInfo<BizConsumer> page = new PageInfo<BizConsumer>(dataList);
        return page;
    }

    /**
     * 新增数据
     *
     * @param bizConsumer 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BizConsumer bizConsumer) {
        return bizConsumerDao.insert(bizConsumer);
    }

    /**
     * 修改数据
     *
     * @param bizConsumer 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizConsumer bizConsumer) {
        return bizConsumerDao.updateById(bizConsumer);
    }

    /**
     * 通过主键id删除数据
     *
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return bizConsumerDao.deleteById(id);
    }

    @Override
    public PageInfo<BizSupplier> mohuSelect(int pageNum, int pageSize, BizConsumer bizConsumer) {
        PageHelper.startPage(pageNum, pageSize);
        List<BizSupplier> dataList = bizConsumerDao.mohuSelect(bizConsumer);
        PageInfo<BizSupplier> page = new PageInfo<BizSupplier>(dataList);
        return page;
    }
}