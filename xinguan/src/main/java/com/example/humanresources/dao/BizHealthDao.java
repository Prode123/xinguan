package com.example.humanresources.dao;

import com.example.humanresources.entity.BizHealth;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (BizHealth)表数据库访问层
 * @author ZF
 */

@Mapper
public interface BizHealthDao {

    //通过ID查询
    BizHealth selectById(Long id);

    /**
     * 通过username查询该用户所有数据
     * @param username
     * @return
     */
    List<BizHealth> selectByUserUsername(String  username);

    /**
     * 查询当天的健康打卡记录
     * @param username
     * @return
     */
    BizHealth selectByUserIdIsReport(String username);

    //查询所有数据
    List<BizHealth> selectAll();


    //新增数据
    int insert(BizHealth bizHealth);

    //修改数据
    int updateById(BizHealth bizHealth);

    //通过主键id删除数据
    int deleteById(Long id);

}