package com.example.humanresources.dao;

import com.example.humanresources.entity.TbRoleMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色菜单关联表(TbRoleMenu)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbRoleMenuDao {

    //通过ID查询
    TbRoleMenu selectById(int id);

    //查询所有数据
    List<TbRoleMenu> selectAll();


    //新增数据
    int insert(TbRoleMenu tbRoleMenu);

    //修改数据
    int updateById(TbRoleMenu tbRoleMenu);

    //通过主键id删除数据
    int deleteById(int id);

    List<TbRoleMenu> selectByRoleId(long roleId);

}