package com.example.humanresources.service.impl;

import com.example.humanresources.dao.TbUserDao;
import com.example.humanresources.entity.BizHealth;
import com.example.humanresources.dao.BizHealthDao;
import com.example.humanresources.entity.TbUser;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.example.humanresources.service.BizHealthService;
import com.example.humanresources.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * (BizHealth)表服务实现类
 * @author ZF
 */
@Service
@Transactional
public class BizHealthServiceImpl implements BizHealthService {
    @Autowired
    private BizHealthDao bizHealthDao;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private TbUserDao tbUserDao;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizHealth selectById(Long id) {
        return bizHealthDao.selectById(id);
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
        List<BizHealth> dataList = bizHealthDao.selectAll();
        PageInfo<BizHealth> page = new PageInfo<BizHealth>(dataList);
        return page;
    }

//    @Override
//    public List<BizHealth> selectByUserId(int userId) {
//        try{
//            return this.bizHealthDao.selectByUserId(userId);
//        }catch (Exception e){
//            throw new ServiceException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    /**
     * 当天健康查询
     * @return
     */
    @Override
    public BizHealth selectByUserIdIsReport() {
        String token = httpServletRequest.getHeader("Authorization");

        String username = JwtUtil.getUsername(token);
        //System.out.println(username);
        try {
            return this.bizHealthDao.selectByUserIdIsReport(username);
        }catch (Exception e){
            throw new ServiceException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 用户健康查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<BizHealth> selectPageInfoByUserId(int pageNum, int pageSize) {
        String token = httpServletRequest.getHeader("Authorization");

        String username = JwtUtil.getUsername(token);
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<BizHealth> bizHealths = bizHealthDao.selectByUserUsername(username);
            return new PageInfo<BizHealth>(bizHealths);
        }catch (Exception e){
            throw new ServiceException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增数据
     * @param bizHealth 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BizHealth bizHealth) {
        String token = httpServletRequest.getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        //查询用户的信息
        TbUser tbUser = tbUserDao.getUserByUsername(username);
        //获取用用户的的id并添加
        bizHealth.setId(tbUser.getId());
        bizHealth.setCreateTime(new Date());
        try {
            return bizHealthDao.insert(bizHealth);
        }catch (Exception e){
            throw  new ServiceException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改数据
     * @param bizHealth 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizHealth bizHealth) {
        return bizHealthDao.updateById(bizHealth);
    }

    /**
     * 通过主键id删除数据
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return bizHealthDao.deleteById(id);
    }
}