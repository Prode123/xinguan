package com.example.humanresources.dao;

import com.example.humanresources.entity.TbRole;
import com.example.humanresources.entity.TbUserRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联表(TbUserRole)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbUserRoleDao {

    //通过ID查询
    TbUserRole selectById(Long id);

    //查询所有数据
    List<TbUserRole> selectAll();


    //新增数据
    int insert(TbUserRole tbUserRole);

    //修改数据
    int updateById(TbUserRole tbUserRole);

    //通过主键id删除数据
    int deleteById( );

}