package com.example.humanresources.service.impl;

import com.example.humanresources.dao.*;
import com.example.humanresources.entity.*;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.example.humanresources.service.BizInStockService;
import com.example.humanresources.utils.UUIDUitl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * (BizInStock)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class BizInStockServiceImpl implements BizInStockService {
    @Autowired
    private BizInStockDao bizInStockDao;
    @Autowired
    private BizSupplierDao supplierDao;
    @Autowired
    private BizProductDao productDao;
    @Autowired
    private BizInStockInfoDao inStockInfoDao;
    @Autowired
    private BizProductStockDao productStockDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizInStock selectById(Long id) {
        return bizInStockDao.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param pageNum  查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public Page<BizInStock> selectAll(int pageNum, int pageSize, BizInStock inStock) {
        PageHelper.startPage(pageNum, pageSize);
        List<BizInStock> dataList = bizInStockDao.selectAllByStatus(inStock);
        PageInfo<BizInStock> page = new PageInfo<>(dataList);
        return new Page<>(page.getTotal(), dataList);
    }

    @Override
    public int updateStatusById(Long id, int status) {
        BizInStock inStock = new BizInStock();
        inStock.setStatus(status);
        inStock.setId(id);
        inStock.setModified(new Date());
        int code = bizInStockDao.updateById(inStock);
        if (code == 0) {
            throw new ServiceException(ErrorCodeEnum.PRODUCT_NOT_FOUND);
        }
        return code;
    }

    /**
     * 新增数据
     */
    @Override
    public int insert(BizInStock bizInStock) {
        if (bizInStock.getSupplierId() == null) {
            BizSupplier supplier = bizInStock.createBizSupplier();
            supplierDao.insert(supplier);
            Long supplierId = supplier.getId();
            bizInStock.setSupplierId(supplierId);
        }
        Date date = new Date();
        bizInStock.initProductNumber();
        String inNum = UUIDUitl.createUUID();
        bizInStock.setInNum(inNum);
        bizInStock.setCreateTime(date);
        bizInStock.setModified(date);
        bizInStock.setStatus(2);
        bizInStockDao.insert(bizInStock);

        List<Products> products = bizInStock.getProducts();

        for (Products product : products) {
            BizProduct bizProduct = productDao.selectById(product.getProductId());
            BizInStockInfo inStockInfo = new BizInStockInfo();
            inStockInfo.setInNum(inNum);
            inStockInfo.setPNum(bizProduct.getPNum());
            inStockInfo.setProductNumber(product.getProductNumber());
            inStockInfo.setCreateTime(date);
            inStockInfo.setModifiedTime(date);
            inStockInfoDao.insert(inStockInfo);
        }

        return 1;
    }

    /**
     * 修改数据
     *
     * @param bizInStock 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizInStock bizInStock) {
        return bizInStockDao.updateById(bizInStock);
    }

    /**
     * 通过主键id删除数据
     *
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        BizInStock inStock = selectById(id);
        inStockInfoDao.deleteByInNum(inStock.getInNum());
        return bizInStockDao.deleteById(id);
    }

    @Override
    public BizInStockDetail selectDetail(Long id, Integer pageNum) {
        BizInStock inStock = bizInStockDao.selectById(id);
        BizInStockDetail inStockDetail = BizInStockDetail.create(inStock);

        BizSupplier supplier = supplierDao.selectById(inStock.getSupplierId());
        inStockDetail.setSupplierVO(supplier);

        PageHelper.startPage(pageNum, 3);
        List<BizProduct> bizProducts = productDao.selectProductInDetail(inStock.getInNum());
        PageInfo<BizProduct> page = new PageInfo<>(bizProducts);
        inStockDetail.setTotal(page.getTotal());
        inStockDetail.setItemVOS(bizProducts);

        return inStockDetail;
    }

    @Override
    public int publish(Long id) {
        BizInStock inStock = bizInStockDao.selectById(id);
        updateStatusById(id, 0);
        List<BizProduct> products = productDao.selectProductInDetail(inStock.getInNum());
        for (BizProduct product : products) {
            BizProductStock productStock = productStockDao.selectByPNum(product.getPNum());
            productStock.setStock(productStock.getStock() + product.getCount());
            productStockDao.updateById(productStock);
        }
        return 0;
    }
}