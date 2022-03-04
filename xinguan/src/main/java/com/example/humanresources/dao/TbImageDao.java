package com.example.humanresources.dao;

import com.example.humanresources.entity.TbImage;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TbImage)表数据库访问层
 * @author ZF
 */

@Mapper
public interface TbImageDao {

    //通过ID查询
    TbImage selectById(Long id);

    //查询所有数据
    List<TbImage> selectAll();

    /**
     * 模糊查询所有信息
     * @param tbImage
     * @return
     */
    List<TbImage> selectVagueAll(TbImage tbImage);

    //新增数据
    int insert(TbImage tbImage);

    //修改数据
    int updateById(TbImage tbImage);

    //通过主键id删除数据
    int deleteById(Long id);

}