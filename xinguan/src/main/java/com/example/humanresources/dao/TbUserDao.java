package com.example.humanresources.dao;

import com.example.humanresources.entity.TbRole;
import com.example.humanresources.entity.TbUser;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户表(TbUser)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbUserDao {

    //通过ID查询
    TbUser selectById(Long id);

    //通过username查询
    TbUser getUserByUsername(String username);

    //查询所有数据
    List<TbUser> selectAll();


    //新增数据
    int insert(TbUser tbUser);

    //修改数据
    int updateById(TbUser tbUser);

    //通过主键id删除数据
    int deleteById(Long id);

    //通过用户名找到对应的角色的roleId
    List<Integer> getRoleIdByUsername(String username);

    //模糊查询user表
    List<TbUser> findUserList(TbUser user);


    //根据用户id获取角色对象
    List<TbRole> findRoleListByUserId(Long userId);


}