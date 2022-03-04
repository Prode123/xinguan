package com.example.humanresources.service.impl;

import com.example.humanresources.dao.TbRoleDao;
import com.example.humanresources.dao.TbUserDao;
import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.entity.TbRole;
import com.example.humanresources.entity.TbUser;
import com.example.humanresources.service.TbUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表(TbUser)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Resource
    private TbRoleDao tbRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbUser selectById(Long id) {
        return tbUserDao.selectById(id);
    }

    /**
     * 通过username查询单条数据
     *
     * @return 实例对象
     */
    @Override
    public TbUser getUserByUsername(String username) {
        return tbUserDao.getUserByUsername(username);
    }

    /**
     * 判断是否登录成功
     *
     * @param user1 前端传的user
     * @param user2 通过username从数据库获取的user
     * @return 返回是否登录成功
     */
    @Override
    public Boolean login(TbUser user1, TbUser user2) {
        if (user1.getUsername().equals(user2.getUsername())) {

        }
        return null;
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
        List<TbUser> dataList = tbUserDao.selectAll();
        PageInfo<TbUser> page = new PageInfo<TbUser>(dataList);
        return page;
    }

    /**
     * 不分页查询所有用户
     *
     * @return
     */
    @Override
    public List<TbUser> selectAllNotPaging() {
        return tbUserDao.selectAll();
    }

    /**
     * 新增数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbUser tbUser) {
        tbUser.setStatus(0);
        tbUser.setCreateTime(new Date());
        tbUser.setModifiedTime(new Date());
        tbUser.setType(1);
        return tbUserDao.insert(tbUser);
    }

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbUser tbUser) {
        return tbUserDao.updateById(tbUser);
    }

    /**
     * 通过主键id删除数据
     *
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return tbUserDao.deleteById(id);
    }

    /**
     * 生成菜单树
     */
    @Override
    public List<TbMenu> findMenu(String username) {
        //先找到所有菜单
        List<TbMenu> menus = tbRoleDao.findMenuByRoleId(tbUserDao.getRoleIdByUsername(username));
        //菜单对象中有每一个菜单的parentId，根据所有菜单及其父id，生成菜单树
        return menus.stream().filter(tbMenu ->
                tbMenu.getParentId() == 0
        ).map((menu) -> {
            menu.setChildren(getMyChildren(menu, menus));
            return menu;
        }).collect(Collectors.toList());
    }

    //    递归查找所有菜单的子菜单
//    root为当前菜单 all是所有菜单
    private List<TbMenu> getMyChildren(TbMenu root, List<TbMenu> all) {
        List<TbMenu> children = all.stream().filter(tbMenu -> {
            return tbMenu.getParentId().equals(root.getId());
        }).map(tbMenu -> {
//   找到子菜单
            tbMenu.setChildren(getMyChildren(tbMenu, all));
            return tbMenu;
        }).collect(Collectors.toList());
        return children;
    }

    /**
     * 模糊查询用户列表
     */
    @Override
    public PageInfo<TbUser> findUserList(Integer pageNum, Integer pageSize, TbUser user) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbUser> dataList = tbUserDao.findUserList(user);
        return new PageInfo<TbUser>(dataList);
    }

    /**
     * 根据用户id获取角色对象
     */
    @Override
    public List<TbRole> findRoleListByUserId(Long userId) {
        return tbUserDao.findRoleListByUserId(userId);
    }
}