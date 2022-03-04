package com.example.humanresources.dao;

import com.example.humanresources.entity.TbMenu;
import com.example.humanresources.entity.TbRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(TbRole)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbRoleDao {

    //通过ID查询
    TbRole selectById(Long id);

    //查询所有数据
    List<TbRole> selectAll();


    //新增数据
    int insert(TbRole tbRole);

    //修改数据
    int updateById(TbRole tbRole);

    //通过主键id删除数据
    int deleteById(Long id);

    List<TbRole> selectByRoleName(String roleName);

    List<TbMenu> findMenuByRoleId(List<Integer> roleId);

}