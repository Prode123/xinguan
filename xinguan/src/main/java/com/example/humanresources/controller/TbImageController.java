package com.example.humanresources.controller;

import com.example.humanresources.annotation.Operation;
import com.example.humanresources.entity.TbImage;
import com.example.humanresources.service.TbImageService;
import com.example.humanresources.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * (TbImage)表控制层
 *
 * @author makejava
 * @since 2021-12-06 19:09:03
 */
@RestController
@RequestMapping("upload")
public class TbImageController {
/**
 * 服务对象
 */
@Resource
private TbImageService tbImageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbImage selectByPrimaryKey(Long id) {
            return this.tbImageService.selectById(id);
        }

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("image")
    @Operation("上传文件")
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        return R.ok().setData(tbImageService.uploadFileAvatar(file));
    }

    /**
     * 删除图片
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    @Operation("删除图片")
    public R  deleteByIdFileAvatar(@PathVariable Long id){
        return R.ok().setData(tbImageService.deleteByIdFileAvatar(id));
    }

    /**
     * 模糊查询附件列表,分页
     * @param pageNum
     * @param pageSize
     * @param tbImage
     * @return
     */
    @GetMapping("findImageList")
    public R selectVagueAll(int pageNum, int pageSize, TbImage tbImage){
        return R.ok().setData(tbImageService.selectVagueAll(pageNum,pageSize,tbImage));
    }

}