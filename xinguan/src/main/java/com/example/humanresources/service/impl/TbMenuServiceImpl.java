package com.example.humanresources.service.impl;

import com.example.humanresources.dao.TbUserDao;
import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.dao.TbMenuDao;
import com.example.humanresources.entity.TbUser;
import com.example.humanresources.service.TbMenuService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单表(TbMenu)表服务实现类
 * @author ZF
 */
@Service
@Transactional
public class TbMenuServiceImpl implements TbMenuService {
   @Autowired
    private TbMenuDao tbMenuDao;
   @Resource
   private TbUserDao tbUserDao;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbMenu selectById(Long id) {
        return tbMenuDao.selectById(id);
    }

    /**
     * 查询多条数据
     * @param pageNum 查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public PageInfo<TbMenu> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbMenu> dataList = tbMenuDao.selectAll();
        return new PageInfo<>(dataList);
    }


    /**
     * 新增数据
     * @param tbMenu 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbMenu tbMenu) {
        return tbMenuDao.insert(tbMenu);
    }

    /**
     * 修改数据
     * @param tbMenu 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbMenu tbMenu) {
        return tbMenuDao.updateById(tbMenu);
    }

    /**
     * 通过主键id删除数据
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return tbMenuDao.deleteById(id);
    }

    @Override
    public TbMenu selectByMenuName(String menuName) {
        TbMenu tbMenus = tbMenuDao.selectByMenuName(menuName);
        return tbMenus;
    }
}