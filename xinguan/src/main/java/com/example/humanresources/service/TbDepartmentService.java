package com.example.humanresources.service;

import com.example.humanresources.entity.DepartmentNum;
import com.example.humanresources.entity.TbDepartment;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * (TbDepartment)表服务接口
 *
 * @author ZF
 */
public interface TbDepartmentService {

    //通过ID查询
    TbDepartment selectById(Long id);

    //查询所有数据并分页
    List<TbDepartment> selectAll();


    //新增数据
    int insert(TbDepartment tbDepartment);

    //修改数据
    int updateById(TbDepartment tbDepartment);

    //通过主键id删除数据
    int deleteById(Long id);

    //通过名字查询
    PageInfo selectByName(int pageNum, int pageSize, String name);

    //查询人数
    List<DepartmentNum> selectNum();

    //查询所有部门不分页
    List<TbDepartment> selectAllTbDepartment();

}