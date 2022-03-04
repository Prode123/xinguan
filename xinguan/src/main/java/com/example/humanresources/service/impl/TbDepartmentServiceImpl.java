package com.example.humanresources.service.impl;

import com.example.humanresources.dao.TbDepartmentDao;
import com.example.humanresources.entity.DepartmentNum;
import com.example.humanresources.entity.TbDepartment;
import com.example.humanresources.entity.TbDepartmentResultMap;
import com.example.humanresources.service.TbDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TbDepartment)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class TbDepartmentServiceImpl implements TbDepartmentService {
    @Autowired
    private TbDepartmentDao tbDepartmentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbDepartment selectById(Long id) {
        TbDepartment tbDepartment = new TbDepartment();
        TbDepartmentResultMap tbDepartmentResultMap = tbDepartmentDao.selectById(id);
        tbDepartment.setId(tbDepartmentResultMap.getId());
        tbDepartment.setName(tbDepartmentResultMap.getName());
        tbDepartment.setPhone(tbDepartmentResultMap.getPhone());
        tbDepartment.setAddress(tbDepartmentResultMap.getAddress());
        tbDepartment.setCreateTime(tbDepartmentResultMap.getCreateTime());
        tbDepartment.setModifiedTime(tbDepartmentResultMap.getModifiedTime());
        List<DepartmentNum> departmentNums = tbDepartmentDao.selectNum();
        for (DepartmentNum departmentNum : departmentNums) {
            if (departmentNum.getDepartmentId() == tbDepartmentResultMap.getId()) {
                tbDepartment.setNum(departmentNum.getNum());
            } else {
                tbDepartment.setNum(0);
            }
        }
        return tbDepartment;
    }

    /**
     * 查询多条数据
     *
     * @param pageNum  查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbDepartment> selectAll() {
        List<TbDepartmentResultMap> dataList = tbDepartmentDao.selectAll();
        List<DepartmentNum> num = tbDepartmentDao.selectNum();
        List<TbDepartment> tbDepartments = new ArrayList<>();
        for (TbDepartmentResultMap tbDepartment : dataList) {
            TbDepartment tbDepartment1 = new TbDepartment();
            tbDepartment1.setId(tbDepartment.getId());
            tbDepartment1.setPhone(tbDepartment.getPhone());
            tbDepartment1.setAddress(tbDepartment.getAddress());
            tbDepartment1.setCreateTime(tbDepartment.getCreateTime());
            tbDepartment1.setModifiedTime(tbDepartment.getModifiedTime());
            tbDepartment1.setName(tbDepartment.getName());
            for (DepartmentNum i : num) {
                if (i.getDepartmentId() == tbDepartment.getId()) {
                    tbDepartment1.setNum(i.getNum());
                }
            }
            tbDepartments.add(tbDepartment1);
        }
        return tbDepartments;
    }

    /**
     * 新增数据
     *
     * @param tbDepartment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TbDepartment tbDepartment) {
        Date date = new Date();
        tbDepartment.setCreateTime(date);
        return tbDepartmentDao.insert(tbDepartment);
    }

    /**
     * 修改数据
     *
     * @param tbDepartment 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TbDepartment tbDepartment) {
        Date date = new Date();
        tbDepartment.setModifiedTime(date);
        return tbDepartmentDao.updateById(tbDepartment);
    }

    /**
     * 通过主键id删除数据
     *
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        return tbDepartmentDao.deleteById(id);
    }

    @Override
    public PageInfo selectByName(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbDepartmentResultMap> tbDepartments1 = tbDepartmentDao.selectByName(name);
        PageInfo<TbDepartmentResultMap> page1 = new PageInfo<>(tbDepartments1);

        List<DepartmentNum> num = tbDepartmentDao.selectNum();
        List<TbDepartment> tbDepartments = new ArrayList<>();
        for (TbDepartmentResultMap tbDepartment : tbDepartments1) {
            TbDepartment tbDepartment1 = new TbDepartment();
            tbDepartment1.setId(tbDepartment.getId());
            tbDepartment1.setPhone(tbDepartment.getPhone());
            tbDepartment1.setAddress(tbDepartment.getAddress());
            tbDepartment1.setCreateTime(tbDepartment.getCreateTime());
            tbDepartment1.setModifiedTime(tbDepartment.getModifiedTime());
            tbDepartment1.setName(tbDepartment.getName());
            for (DepartmentNum i : num) {
                if (i.getDepartmentId() == tbDepartment.getId()) {
                    tbDepartment1.setNum(i.getNum());
                }
            }
            tbDepartments.add(tbDepartment1);
        }
        PageInfo<TbDepartment> page = new PageInfo<TbDepartment>(tbDepartments);
        page.setTotal(page1.getTotal());
        return page;
    }

    @Override
    public List<DepartmentNum> selectNum() {
        List<DepartmentNum> num = tbDepartmentDao.selectNum();
        return num;
    }

    @Override
    public List<TbDepartment> selectAllTbDepartment() {
        List<TbDepartmentResultMap> dataList = tbDepartmentDao.selectAll();
        List<DepartmentNum> num = tbDepartmentDao.selectNum();
        List<TbDepartment> tbDepartments = new ArrayList<>();
        for (TbDepartmentResultMap tbDepartment : dataList) {
            TbDepartment tbDepartment1 = new TbDepartment();
            tbDepartment1.setId(tbDepartment.getId());
            tbDepartment1.setPhone(tbDepartment.getPhone());
            tbDepartment1.setAddress(tbDepartment.getAddress());
            tbDepartment1.setCreateTime(tbDepartment.getCreateTime());
            tbDepartment1.setModifiedTime(tbDepartment.getModifiedTime());
            tbDepartment1.setName(tbDepartment.getName());
            for (DepartmentNum i : num) {
                if (i.getDepartmentId() == tbDepartment.getId()) {
                    tbDepartment1.setNum(i.getNum());
                }
            }
            tbDepartments.add(tbDepartment1);
        }
        return tbDepartments;
    }
}