package com.example.humanresources.service.impl;

import com.example.humanresources.entity.TbUserRole;
import com.example.humanresources.dao.TbUserRoleDao;
import com.example.humanresources.service.TbUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关联表(TbUserRole)表服务实现类
 * @author ZF
 */
@Service
@Transactional
public class TbUserRoleServiceImpl implements TbUserRoleService {
   @Autowired
    private TbUserRoleDao tbUserRoleDao;

    /**
     * 通过ID查询单条数据
     * @param  主键
     * @return 实例对象
     */
    @Override
    public TbUserRole selectById(Long id) {
        return tbUserRoleDao.selectById(id);
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
        List<TbUserRole> dataList = tbUserRoleDao.selectAll();
        PageInfo<TbUserRole> page = new PageInfo<TbUserRole>(dataList);
        return page;
    }

    /**
     * 新增数据
     * @param tbUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbUserRole tbUserRole) {
        return tbUserRoleDao.insert(tbUserRole);
    }

    /**
     * 修改数据
     * @param tbUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbUserRole tbUserRole) {
        return tbUserRoleDao.updateById(tbUserRole);
    }

    /**
     * 通过主键id删除数据
     * @param  主键
     */
    @Override
    public int deleteById(Long id) {
        return tbUserRoleDao.deleteById();
    }
}