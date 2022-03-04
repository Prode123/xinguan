package com.example.humanresources.dao;

import com.example.humanresources.entity.DepartmentNum;
import com.example.humanresources.entity.TbDepartment;
import java.util.List;

import com.example.humanresources.entity.TbDepartmentResultMap;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TbDepartment)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbDepartmentDao {

    //通过ID查询
    TbDepartmentResultMap selectById(Long id);

    //查询所有数据
    List<TbDepartmentResultMap> selectAll();


    //新增数据
    int insert(TbDepartment tbDepartment);

    //修改数据
    int updateById(TbDepartment tbDepartment);

    //通过主键id删除数据
    int deleteById(Long id);

    //通过名字查询
    List<TbDepartmentResultMap> selectByName(String name);

    //查询部门的人数
    List<DepartmentNum> selectNum();

    List<TbDepartment> selectAllTbDepartment();

}