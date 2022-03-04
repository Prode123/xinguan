package com.example.humanresources.service;

import com.example.humanresources.entity.BizHealth;
import java.util.List;
import com.github.pagehelper.PageInfo;


/**
 * (BizHealth)表服务接口
 * @author ZF
 */
public interface BizHealthService {

    //通过ID查询
    BizHealth selectById(Long id);

    //查询所有数据并分页
    PageInfo<BizHealth> selectAll(int pageNum, int pageSize);

    /**
     * 通过User_id查询该用户所有数据
     * @param userId
     * @return
     */
    //List<BizHealth> selectByUserId(int userId);

    /**
     * 查询当天的健康打卡记录
     * @return
     */
    BizHealth selectByUserIdIsReport();

    /**
     * 通过User_id查询该用户所有数据,并分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<BizHealth> selectPageInfoByUserId(int pageNum, int pageSize);

    //新增数据
    int insert(BizHealth bizHealth);

    //修改数据
    int updateById(BizHealth bizHealth);

    //通过主键id删除数据
    int deleteById(Long id);

}