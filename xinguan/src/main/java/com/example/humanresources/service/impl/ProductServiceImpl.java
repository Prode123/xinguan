package com.example.humanresources.service.impl;

import com.example.humanresources.dao.BizProductDao;
import com.example.humanresources.dao.BizProductStockDao;
import com.example.humanresources.entity.BizProduct;
import com.example.humanresources.entity.BizProductStock;
import com.example.humanresources.entity.Page;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.example.humanresources.service.ProductService;
import com.example.humanresources.utils.UUIDUitl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * (BizProduct)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private BizProductDao productDao;
    @Autowired
    private BizProductStockDao stockDao;

    /**
     * 通过ID和状态查询单条数据
     *
     * @return 实例对象
     */
    @Override
    public BizProduct selectById(Long id, int status) {
        BizProduct product = productDao.selectById(id);
        if (product == null) {
            throw new ServiceException(ErrorCodeEnum.PRODUCT_NOT_FOUND);
        } else if (status != product.getStatus()) {
            throw new ServiceException(ErrorCodeEnum.PRODUCT_STATUS_ERROR);
        }
        return product;
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BizProduct product) {
        product.initCategory();
        product.setPNum(UUIDUitl.createUUID());
        product.setCreateTime(new Date());
        product.setModifiedTime(new Date());
        product.setStatus(2);
        try {
            return productDao.insert(product);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeEnum.BODY_NOT_MATCH);
        }
    }

    /**
     * 修改数据
     *
     * @param bizProduct 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizProduct bizProduct) {
        bizProduct.initCategory();
        bizProduct.setModifiedTime(new Date());
        return productDao.updateById(bizProduct);
    }

    /**
     * 恢复数据
     */
    @Override
    public int updateStatusByIdInt(Long id, int status) {
        BizProduct product = new BizProduct();
        product.setId(id);
        product.setStatus(status);
        product.setModifiedTime(new Date());
        int code = productDao.updateById(product);
        if (code == 0) {
            throw new ServiceException(ErrorCodeEnum.PRODUCT_NOT_FOUND);
        }
        return code;

    }

    /**
     * 通过主键id删除数据
     */
    @Override
    public int deleteById(Long id) {
        BizProduct product = selectById(id, 1);
        BizProductStock productStock = stockDao.selectByPNum(product.getPNum());
        if (productStock.getStock() != 0) {
            throw new ServiceException(ErrorCodeEnum.PRODUCT_STOCK_NOT_NULL);
        }
        stockDao.deleteByPNum(product.getPNum());
        return productDao.deleteById(id);

    }


    /**
     * 模糊查询所有库存
     */
    @Override
    public List<BizProduct> findAllStocks(int pageNum, int pageSize, BizProduct product) {
        try {
            product.initCategory();
            PageHelper.startPage(pageNum, pageSize);
            List<BizProduct> dataList = productDao.selectAllStock(product);
            PageInfo<BizProduct> page = new PageInfo<>(dataList);
            return page.getList();
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取全部物资信息
     */
    @Override
    public Page<BizProduct> findProductList(int pageNum, int pageSize, BizProduct product) {
        try {
            product.initCategory();
            PageHelper.startPage(pageNum, pageSize);
            List<BizProduct> dataList = productDao.selectAll(product);
            PageInfo<BizProduct> page = new PageInfo<>(dataList);
            return new Page<BizProduct>(page.getTotal(), page.getList());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public int publish(Long id) {
        BizProduct product = selectById(id, 2);
        if (product == null) {
            throw new ServiceException(ErrorCodeEnum.PRODUCT_NOT_FOUND);
        }
        BizProductStock stock = new BizProductStock();
        stock.setPNum(product.getPNum());
        stock.setStock(0L);
        stockDao.insert(stock);
        return updateStatusByIdInt(id, 0);
    }


    /**
     * 模糊查询库存列表
     */
    @Override
    public Page<BizProduct> findProductStocks(int pageNum, int pageSize, BizProduct product) {
        try {
            product.initCategory();
            PageHelper.startPage(pageNum, pageSize);
            List<BizProduct> dataList = productDao.selectAllStock(product);
            PageInfo<BizProduct> page = new PageInfo<>(dataList);
            return new Page<BizProduct>(page.getTotal(), page.getList());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }
}