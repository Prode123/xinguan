package com.example.humanresources.dao;

import com.example.humanresources.entity.TbMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单表(TbMenu)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbMenuDao {

    //通过ID查询
    TbMenu selectById(Long id);

    //查询所有数据
    List<TbMenu> selectAll();


    //新增数据
    int insert(TbMenu tbMenu);

    //修改数据
    int updateById(TbMenu tbMenu);

    //通过主键id删除数据
    int deleteById(Long id);

    TbMenu selectByMenuName(String menuName);

}