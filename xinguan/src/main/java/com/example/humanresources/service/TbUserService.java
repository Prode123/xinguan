package com.example.humanresources.service;

import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.entity.TbRole;
import com.example.humanresources.entity.TbUser;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * 用户表(TbUser)表服务接口
 * @author ZF
 */
public interface TbUserService {

    //通过ID查询
    TbUser selectById(Long id);

    //通过username查询
    TbUser getUserByUsername(String username);

    //判断是否登录成功
    Boolean login(TbUser user1, TbUser user2);

    //查询所有数据并分页
    PageInfo<TbUser> selectAll(int pageNum, int pageSize);

    //查询所有数据，但不分页
    List<TbUser> selectAllNotPaging();


    //新增数据
    int insert(TbUser tbUser);

    //修改数据
    int updateById(TbUser tbUser);

    //通过主键id删除数据
    int deleteById(Long id);

    //通过username加载菜单树
    List<TbMenu> findMenu(String username);

    //模糊查询user表
    PageInfo<TbUser> findUserList(Integer pageNum, Integer pageSize, TbUser user);

    //根据用户id获取角色对象
    List<TbRole> findRoleListByUserId(Long userId);

}