package com.example.humanresources.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.humanresources.entity.BizHealth;
import com.example.humanresources.entity.TbImage;
import com.example.humanresources.dao.TbImageDao;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.example.humanresources.service.TbImageService;
import com.example.humanresources.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import java.util.List;

/**
 * (TbImage)表服务实现类
 * @author ZF
 */
@Service
@Transactional
public class TbImageServiceImpl implements TbImageService {

   @Autowired
    private TbImageDao tbImageDao;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbImage selectById(Long id) {
        return tbImageDao.selectById(id);
    }

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
//            BufferedImage sourceImg = ImageIO.read(new FileInputStream((File) file));
            TbImage tbImage = new TbImage();
            BufferedImage sourceImg = ImageIO.read(file.getInputStream());
            // 源图大小
            tbImage.setSize(file.getSize() / 1024);
//            String contentType = file.getContentType();
//            System.out.println(contentType);
            //源片的类型
            tbImage.setMediaType(file.getContentType());
            // 源图宽度
            tbImage.setWidth(sourceImg.getWidth());
            // 源图高度
            tbImage.setHeight(sourceImg.getHeight());
            tbImage.setCreateTime(new Date());

            //创建OSS实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            fileName = uuid + fileName;
            String substring = fileName.substring(fileName.lastIndexOf(".")+1);
            tbImage.setSuffix(substring);
            //2 把文件按照日期进行分类
            //获取当前日期
            //   2019/11/12
            //String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            //  xinguan/ewtqr313401.jpg
            fileName ="xinguan"+  "/" + fileName;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //  https://edu-teacher-head-101.oss-cn-beijing.aliyuncs.com/01.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            tbImage.setCreateTime(new Date());
            tbImage.setPath(url);
            tbImageDao.insert(tbImage);
            return url;


        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询多条数据
     * @param pageNum 查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbImage> dataList = tbImageDao.selectAll();
        PageInfo<TbImage> page = new PageInfo<TbImage>(dataList);
        return page;
    }

    /**
     * 新增数据
     * @param tbImage 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbImage tbImage) {
        return tbImageDao.insert(tbImage);
    }

    /**
     * 修改数据
     * @param tbImage 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbImage tbImage) {
        return tbImageDao.updateById(tbImage);
    }

    /**
     * 通过主键id删除数据
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return tbImageDao.deleteById(id);
    }

    /**
     * 删除OSS镜像并删除数据库
     * @param id
     * @return
     */
    @Override
    public int deleteByIdFileAvatar(Long id) {
        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        //创建OSS实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        TbImage tbImage = tbImageDao.selectById(id);
        String fileName=tbImage.getPath();
        fileName = "xinguan/"+fileName.substring(fileName.lastIndexOf("/") + 1);
        System.out.println(fileName);
        ossClient.deleteObject(bucketName, fileName);

        // 关闭OSSClient。
        ossClient.shutdown();

        int i = tbImageDao.deleteById(id);


        return i;
    }

    /**
     * 模糊查询附件列表,分页
     * @param pageNum
     * @param pageSize
     * @param tbImage
     * @return
     */
    @Override
    public PageInfo<TbImage> selectVagueAll(int pageNum, int pageSize, TbImage tbImage) {
        try{
            PageHelper.startPage(pageNum,pageSize);
            List<TbImage> tbImages = tbImageDao.selectVagueAll(tbImage);
            return new PageInfo<TbImage>(tbImages);
        }catch (Exception e){
            throw new ServiceException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }
}