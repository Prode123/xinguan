package com.example.humanresources.service;

import com.example.humanresources.entity.TbUserRole;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * 用户角色关联表(TbUserRole)表服务接口
 * @author ZF
 */
public interface TbUserRoleService {

    //通过ID查询
    TbUserRole selectById(Long id);

    //查询所有数据并分页
    PageInfo<TbUserRole> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(TbUserRole tbUserRole);

    //修改数据
    int updateById(TbUserRole tbUserRole);

    //通过主键id删除数据
    int deleteById(Long id);

}