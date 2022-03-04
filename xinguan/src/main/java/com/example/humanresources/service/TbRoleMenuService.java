package com.example.humanresources.service;

import com.example.humanresources.entity.TbRoleMenu;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * 角色菜单关联表(TbRoleMenu)表服务接口
 * @author ZF
 */
public interface TbRoleMenuService {

    //通过ID查询
    TbRoleMenu selectById(int id);

    //查询所有数据并分页
    PageInfo<TbRoleMenu> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(TbRoleMenu tbRoleMenu);

    //修改数据
    int updateById(TbRoleMenu tbRoleMenu);

    //通过主键id删除数据
    int deleteById(int id);

    //通过角色Id授权
    int authorityByRoleId(long roleId,String[] menuName);

    List<TbRoleMenu> selectByRoleId(long roleId);

}