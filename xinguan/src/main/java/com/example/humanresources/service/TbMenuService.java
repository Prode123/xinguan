package com.example.humanresources.service;

import com.example.humanresources.entity.TbMenu;
import java.util.List;

import com.example.humanresources.entity.TbUser;
import com.github.pagehelper.PageInfo;


/**
 * 菜单表(TbMenu)表服务接口
 * @author ZF
 */
public interface TbMenuService {

    //通过ID查询
    TbMenu selectById(Long id);

    //查询所有数据并分页
    PageInfo<TbMenu> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(TbMenu tbMenu);

    //修改数据
    int updateById(TbMenu tbMenu);

    //通过主键id删除数据
    int deleteById(Long id);

    TbMenu selectByMenuName(String menuName);

}