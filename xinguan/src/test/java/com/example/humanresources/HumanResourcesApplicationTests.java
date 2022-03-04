package com.example.humanresources;

import com.example.humanresources.dao.BizProductCategoryDao;
import com.example.humanresources.entity.BizProductCategory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.humanresources.dao.BizHealthDao;
import com.example.humanresources.dao.TbImageDao;
import com.example.humanresources.entity.TbImage;
import com.example.humanresources.service.TbImageService;
import com.example.humanresources.utils.Encrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

@SpringBootTest
class HumanResourcesApplicationTests {
//    @Autowired
//    BizProductCategoryDao bizProductCategoryDao;
//
//    @Autowired
// private BizHealthDao bizHealthDao;
//    @Autowired
//    private TbImageDao tbImageDao;
//@Autowired
//    private TbImageService tbImageService;
    @Test
    void contextLoads() {

    }
//    @Test
//    public void listWithTree() {
//        PageHelper.startPage(1,2);
//
////        1.查出所有分类
//        List<BizProductCategory> bizProductCategories = bizProductCategoryDao.selectByPid(0l);
//        for (BizProductCategory bizProductCategory : bizProductCategories) {
//
//        }
//
//        //PageInfo<BizProductCategory> page = new PageInfo<>(bizProductCategories);
//        //System.out.println(page);
//    }
//
//    private List<BizProductCategory> getChildrens(BizProductCategory root, List<BizProductCategory> all){
//        List<BizProductCategory> children = all.stream().filter(bizProductCategory -> {
//            return bizProductCategory.getPid() == root.getId();
//        }).map(bizProductCategory->{
////            找到子菜单
//            bizProductCategory.setChildren(getChildrens(bizProductCategory,all));
//            return bizProductCategory;
//        }).sorted((menu1,menu2)->{
////            菜单的排序
//            return (menu1.getSort()==null?0:menu1.getSort())-(menu2.getSort()==null?0:menu2.getSort());
//        }).collect(Collectors.toList());
//        return children;
//    }
//    @Test
//    public void test(){
//        bizHealthDao.selectByUserIdIsReport(5);
//    }
//    @Test
//    public void test1(){
//        Long in= Long.valueOf(37);
//        int i = tbImageService.deleteByIdFileAvatar(in);
//        System.out.println(i);
//    }
//    @Test
//    public void test2(){
//        TbImage tbImage = new TbImage();
//        tbImage.setMediaType("image/jpeg");
//       // tbImage.setSuffix("jpg");
//        List<TbImage> tbImages = tbImageDao.selectVagueAll(tbImage);
//        for (TbImage tbImage1 : tbImages){
//            System.out.println(tbImage1);
//        }
//    }

}
