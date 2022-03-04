package com.example.humanresources.service.impl;

import com.example.humanresources.dao.TbLoginLogDao;
import com.example.humanresources.entity.TbLoginLog;
import com.example.humanresources.service.TbLoginLogService;
import com.example.humanresources.utils.IpAndAddrUtil;
import com.example.humanresources.utils.IpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 登录日志表(TbLoginLog)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class TbLoginLogServiceImpl implements TbLoginLogService {
    @Autowired
    private TbLoginLogDao tbLoginLogDao;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbLoginLog selectById(Long id) {
        return tbLoginLogDao.selectById(id);
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
        List<TbLoginLog> dataList = tbLoginLogDao.selectAll();
        PageInfo<TbLoginLog> page = new PageInfo<TbLoginLog>(dataList);
        return page;
    }

    /**
     * 模糊查询所有数据并分页
     *
     * @param pageNum
     * @param pageSize
     * @param tbLoginLog
     * @return
     */
    @Override
    public PageInfo<TbLoginLog> selectVagueAll(int pageNum, int pageSize, TbLoginLog tbLoginLog) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbLoginLog> tbLoginLogs = tbLoginLogDao.selectByTbLoginLog(tbLoginLog);
        PageInfo<TbLoginLog> tbLoginLogPageInfo = new PageInfo<>(tbLoginLogs);
        return tbLoginLogPageInfo;
    }

    /**
     * 新增数据
     *
     * @param tbLoginLog 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbLoginLog tbLoginLog) {
        tbLoginLog.setIp(IpAndAddrUtil.getIp(httpServletRequest));
        tbLoginLog.setLocation(IpUtil.getAddress(tbLoginLog.getIp()));
        tbLoginLog.setUserBrowser(IpAndAddrUtil.getBrowserName(httpServletRequest));
        tbLoginLog.setUserSystem(IpAndAddrUtil.getOsName(httpServletRequest));
        tbLoginLog.setLoginTime(new Date());
        return tbLoginLogDao.insert(tbLoginLog);
    }

    /**
     * 修改数据
     *
     * @param tbLoginLog 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbLoginLog tbLoginLog) {
        return tbLoginLogDao.updateById(tbLoginLog);
    }

    /**
     * 通过主键id删除数据
     *
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return tbLoginLogDao.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteMoreTbLoginLog(int[] id) {
        return tbLoginLogDao.deleteMoreTbLoginLog(id);
    }
}