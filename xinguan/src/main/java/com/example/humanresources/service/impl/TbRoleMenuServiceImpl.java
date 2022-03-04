package com.example.humanresources.service.impl;

import com.example.humanresources.dao.TbMenuDao;
import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.entity.TbRoleMenu;
import com.example.humanresources.dao.TbRoleMenuDao;
import com.example.humanresources.service.TbRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色菜单关联表(TbRoleMenu)表服务实现类
 * @author ZF
 */
@Service
@Transactional
public class TbRoleMenuServiceImpl implements TbRoleMenuService {
   @Autowired
    private TbRoleMenuDao tbRoleMenuDao;

   @Autowired
   private TbMenuDao tbMenuDao;

    /**
     * 通过ID查询单条数据
     * @param  id 主键
     * @return 实例对象
     */
    @Override
    public TbRoleMenu selectById(int id) { return tbRoleMenuDao.selectById(id); }

    /**
     * 查询多条数据
     * @param pageNum 查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbRoleMenu> dataList = tbRoleMenuDao.selectAll();
        PageInfo<TbRoleMenu> page = new PageInfo<TbRoleMenu>(dataList);
        return page;
    }

    /**
     * 新增数据
     * @param tbRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbRoleMenu tbRoleMenu) {
        return tbRoleMenuDao.insert(tbRoleMenu);
    }

    /**
     * 修改数据
     * @param tbRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbRoleMenu tbRoleMenu) {

        return tbRoleMenuDao.updateById(tbRoleMenu);
    }

    /**
     * 通过主键id删除数据
     * @param  id 主键
     */
    @Override
    public int deleteById(int id) {
        return tbRoleMenuDao.deleteById(id);
    }

    @Override
    public int authorityByRoleId(long roleId,String[] menuName) {
        List<TbMenu> tbMenus=new ArrayList<>();
        for (String menuName1 : menuName){
            TbMenu tbMenu = tbMenuDao.selectByMenuName(menuName1);
            tbMenus.add(tbMenu);
        }
//        for (int i = 0; i < menuName.length; i++) {
//            TbMenu tbMenu = tbMenuDao.selectByMenuName(menuName[0]);
//            tbMenus.add(tbMenu);
//        }
        List<TbRoleMenu> tbRoleMenus = tbRoleMenuDao.selectByRoleId(roleId);
        for (TbRoleMenu tbRoleMenu : tbRoleMenus) {
            int i = tbRoleMenuDao.deleteById(tbRoleMenu.getId());
        }
        for (TbMenu tbMenu : tbMenus){
            TbRoleMenu tbRoleMenu = new TbRoleMenu();
            tbRoleMenu.setRoleId(roleId);
            tbRoleMenu.setMenuId(tbMenu.getId());
            int i = tbRoleMenuDao.insert(tbRoleMenu);
        }
        return 1;
    }

    @Override
    public List<TbRoleMenu> selectByRoleId(long roleId) {
        List<TbRoleMenu> tbRoleMenus = tbRoleMenuDao.selectByRoleId(roleId);
        return tbRoleMenus;
    }
}