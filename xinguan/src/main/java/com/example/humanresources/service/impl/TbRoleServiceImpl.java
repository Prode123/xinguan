package com.example.humanresources.service.impl;

import com.example.humanresources.entity.TbRole;
import com.example.humanresources.dao.TbRoleDao;
import com.example.humanresources.entity.TbRoleExcel;
import com.example.humanresources.entity.TbRoleMenu;
import com.example.humanresources.service.TbRoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色表(TbRole)表服务实现类
 * @author ZF
 */
@Service
@Transactional
public class TbRoleServiceImpl implements TbRoleService {
   @Autowired
    private TbRoleDao tbRoleDao;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbRole selectById(Long id) {
        return tbRoleDao.selectById(id);
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
        List<TbRole> dataList = tbRoleDao.selectAll();
        PageInfo<TbRole> page = new PageInfo<TbRole>(dataList);
        return page;
    }

    /**
     * 新增数据
     * @param tbRole 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbRole tbRole) {
        Date date = new Date();
        tbRole.setCreateTime(date);
        int i = tbRoleDao.insert(tbRole);
        return i;
    }

    /**
     * 修改数据
     * @param tbRole 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbRole tbRole) {
        Date date = new Date();
        tbRole.setModifiedTime(date);
        int i = tbRoleDao.updateById(tbRole);
        return i;
    }

    /**
     * 通过主键id删除数据
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return tbRoleDao.deleteById(id);
    }

    @Override
    public List<TbRoleExcel> selectAll() {
        List<TbRole> tbRoles = tbRoleDao.selectAll();
        List<TbRoleExcel> tbRoleExcels=new ArrayList<>();
        for (TbRole tbRole : tbRoles) {
            TbRoleExcel tbRoleExcel = new TbRoleExcel();
            if (tbRole.getStatus()==0){
                tbRoleExcel.setId(tbRole.getId());
                tbRoleExcel.setCreateTime(tbRole.getCreateTime());
                tbRoleExcel.setModifiedTime(tbRole.getModifiedTime());
                tbRoleExcel.setRemark(tbRole.getRemark());
                tbRoleExcel.setRoleName(tbRole.getRoleName());
                tbRoleExcel.setStatus("已禁用");
            }else if (tbRole.getStatus()==1){
                tbRoleExcel.setId(tbRole.getId());
                tbRoleExcel.setCreateTime(tbRole.getCreateTime());
                tbRoleExcel.setModifiedTime(tbRole.getModifiedTime());
                tbRoleExcel.setRemark(tbRole.getRemark());
                tbRoleExcel.setRoleName(tbRole.getRoleName());
                tbRoleExcel.setStatus("未禁用");
            }
            tbRoleExcels.add(tbRoleExcel);
        }
        System.out.println(tbRoleExcels);
        return tbRoleExcels;
    }

    @Override
    public PageInfo selectByRoleName(int pageNum,int pageSize,String roleName) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbRole> tbRoles = tbRoleDao.selectByRoleName(roleName);
        PageInfo<TbRole> page = new PageInfo<TbRole>(tbRoles);
        return page;
    }


}