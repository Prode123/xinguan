package com.example.humanresources.service;

import com.example.humanresources.entity.TbImage;
import java.util.List;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * (TbImage)表服务接口
 * @author ZF
 */
public interface TbImageService {

    //通过ID查询
    TbImage selectById(Long id);

    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);

    //查询所有数据并分页
    PageInfo<TbImage> selectAll(int pageNum, int pageSize);


    //新增数据
    int insert(TbImage tbImage);

    //修改数据
    int updateById(TbImage tbImage);

    //通过主键id删除数据
    int deleteById(Long id);

    /**
     * 删除OSS镜像并删除数据库
     * @param id
     * @return
     */
    int deleteByIdFileAvatar(Long id);

    /**
     * 模糊查询附件列表,分页
     * @param pageNum
     * @param pageSize
     * @param tbImage
     * @return
     */
    PageInfo<TbImage> selectVagueAll(int pageNum, int pageSize, TbImage tbImage);

}