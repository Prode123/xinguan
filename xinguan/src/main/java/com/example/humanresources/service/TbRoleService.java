package com.example.humanresources.service;

import com.example.humanresources.entity.TbRole;
import java.util.List;

import com.example.humanresources.entity.TbRoleExcel;
import com.github.pagehelper.PageInfo;


/**
 * 角色表(TbRole)表服务接口
 * @author ZF
 */
public interface TbRoleService {

    //通过ID查询
    TbRole selectById(Long id);

    //查询所有数据并分页
    PageInfo<TbRole> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(TbRole tbRole);

    //修改数据
    int updateById(TbRole tbRole);

    //通过主键id删除数据
    int deleteById(Long id);

    //查询所有数据不分页
    List<TbRoleExcel> selectAll();

    //通过角色名查询并分页
    PageInfo selectByRoleName(int pageNum,int pageSize,String roleName);

}