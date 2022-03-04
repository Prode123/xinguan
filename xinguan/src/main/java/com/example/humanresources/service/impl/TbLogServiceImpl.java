package com.example.humanresources.service.impl;

import com.example.humanresources.entity.TbLog;
import com.example.humanresources.dao.TbLogDao;
import com.example.humanresources.service.TbLogService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志表(TbLog)表服务实现类
 * @author ZF
 */
@Service
@Transactional
public class TbLogServiceImpl implements TbLogService {
   @Autowired
    private TbLogDao tbLogDao;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbLog selectById(Long id) {
        return tbLogDao.selectById(id);
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
        List<TbLog> dataList = tbLogDao.selectAll();
        PageInfo<TbLog> page = new PageInfo<TbLog>(dataList);
        return page;
    }

    /**
     * 新增数据
     * @param tbLog 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbLog tbLog) {
        return tbLogDao.insert(tbLog);
    }

    /**
     * 修改数据
     * @param tbLog 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbLog tbLog) {
        return tbLogDao.updateById(tbLog);
    }

    /**
     * 通过主键id删除数据
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return tbLogDao.deleteById(id);
    }

    @Override
    public PageInfo selectByTbl(int pageNum,int pageSize,TbLog tbLog) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbLog> tbLogs = tbLogDao.selectByTbl(tbLog);
        PageInfo<TbLog> page = new PageInfo<TbLog>(tbLogs);
        return page;
    }
}