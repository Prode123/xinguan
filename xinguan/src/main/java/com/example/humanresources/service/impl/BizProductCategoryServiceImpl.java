package com.example.humanresources.service.impl;

import com.example.humanresources.dao.BizProductCategoryDao;
import com.example.humanresources.entity.BizProductCategory;
import com.example.humanresources.entity.BizProductCategoryPlus;
import com.example.humanresources.service.BizProductCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (BizProductCategory)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class BizProductCategoryServiceImpl implements BizProductCategoryService {
    @Autowired
    private BizProductCategoryDao bizProductCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizProductCategory selectById(Long id) {
        return bizProductCategoryDao.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param pageNum  查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public PageInfo selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BizProductCategory> dataList = bizProductCategoryDao.selectAll();
        PageInfo<BizProductCategory> page = new PageInfo<BizProductCategory>(dataList);
        return page;
    }

    /**
     * 新增数据
     *
     * @param bizProductCategory 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BizProductCategory bizProductCategory) {
        return bizProductCategoryDao.insert(bizProductCategory);
    }

    /**
     * 修改数据
     *
     * @param bizProductCategory 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizProductCategory bizProductCategory) {
        return bizProductCategoryDao.updateById(bizProductCategory);
    }

    /**
     * 通过主键id删除数据
     *
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return bizProductCategoryDao.deleteById(id);
    }

    @Override
    public BizProductCategoryPlus listWithTree(int pageNum, int pageSize) {
        // 1.查出所有分类
        List<BizProductCategory> bizProductCategories = bizProductCategoryDao.selectAll();
        for (BizProductCategory bizProductCategory : bizProductCategories) {
            System.out.println(bizProductCategory);
        }
        // 2.组装成父子树形结构
        // 2.1找出所有的一级分类
        List<BizProductCategory> level1Menus = bizProductCategories.stream().filter(bizProductCategory ->
                bizProductCategory.getPid() == 0
        ).map((menu) -> {
            menu.setChildren(getChildrens(menu, bizProductCategories));
            return menu;
        }).sorted((menu1, menu2) -> {
            //            菜单的排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        //        List<BizProductCategory> level1Menus = bizProductCategories.stream().filter((bizProductCategory) -> {
        //            return bizProductCategory.getPid() == 0;
        //        }).collect(Collectors.toList());
        List<BizProductCategory> page = new ArrayList<>();
        int start = (pageNum - 1) * pageSize;
        for (int i = start; i < (start + pageSize > level1Menus.size() ? level1Menus.size() : start + pageSize); i++) {
            page.add(level1Menus.get(i));
        }
        BizProductCategoryPlus bizProductCategoryPlus = new BizProductCategoryPlus();
        bizProductCategoryPlus.setRows(page);
        bizProductCategoryPlus.setTotal(level1Menus.size());
        return bizProductCategoryPlus;
    }

    /**
     * 所有分类
     *
     * @return
     */
    @Override
    public List<BizProductCategory> findALl() {
        // 1.查出所有分类
        List<BizProductCategory> bizProductCategories = bizProductCategoryDao.selectAll();
        // 2.组装成父子树形结构
        // 2.1找出所有的一级分类
        List<BizProductCategory> level1Menus = bizProductCategories.stream().filter(bizProductCategory ->
                bizProductCategory.getPid() == 0
        ).map((menu) -> {
            menu.setChildren(getChildrens(menu, bizProductCategories));
            return menu;
        }).sorted((menu1, menu2) -> {
            //            菜单的排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        //        List<BizProductCategory> level1Menus = bizProductCategories.stream().filter((bizProductCategory) -> {
        //            return bizProductCategory.getPid() == 0;
        //        }).collect(Collectors.toList());
        return level1Menus;
    }


    //递归查找所有菜单的子菜单
    private List<BizProductCategory> getChildrens(BizProductCategory root, List<BizProductCategory> all) {
        List<BizProductCategory> children = all.stream().filter(bizProductCategory -> {
            return bizProductCategory.getPid() == root.getId();
        }).map(bizProductCategory -> {
//            找到子菜单
            bizProductCategory.setChildren(getChildrens(bizProductCategory, all));
            return bizProductCategory;
        }).sorted((menu1, menu2) -> {
//            菜单的排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }
//    @Override
//    public List<BizProductCategory> listWithTree() {
//        //1获取所有list内容
//        List<BizProductCategory> entities = bizProductCategoryDao.selectAll();
//        //2组装成树形结构
//        //2.12.1找到一级分类  使用stream().filter()过滤，过滤categoryEntity中ParentCid=0的，
//        // 把它选出来并使用collect toList使用list集合收集
//        List<BizProductCategory> level1Menus = entities.stream().filter((bizProductCategory) ->{
//            return bizProductCategory.getPid() == 0;
//        }).map((menu)->{//每一个菜单
//            menu.setChildren(getChildrens(menu, entities));//将子类菜单赋给menu并返回过去——子菜单如何获得？通过递归获取子菜单
//            return menu;
//        }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
//                .collect(Collectors.toList());
//        return level1Menus;
//    }
//
//    //递归方法 返回子菜单 递归查找所有菜单的子菜单
//    private List getChildrens(BizProductCategory root, List all){
//        //传入当前菜单CategoryEntity 以及 所有菜单List<CategoryEntity>，在all中找到root
//        List<BizProductCategory> children = all.stream().filter((bizProductCategory) -> {
//            return bizProductCategory.getPid() == root.getCatId();
//            //过滤：选取这个菜单的父节点等于root(当前菜单)的id并返回
//        }).map((categoryEntity)->{
//            //1、找到子菜单
//            categoryEntity.setChildren(getChildrens(categoryEntity, all));//还是需要找到子菜单,使用递归
//            return categoryEntity;
//        }).sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
//                .collect(Collectors.toList());
//        return children;
//        }
//    }


    @Override
    public List<BizProductCategory> selectByPid(Long id) {

        return bizProductCategoryDao.selectByPid(id);
    }

    /**
     * 查询分类下有没有商品
     *
     * @param id
     * @return
     */

    @Override
    public int selectTwo(long id) {
        return bizProductCategoryDao.selectTwo(id);
    }

}