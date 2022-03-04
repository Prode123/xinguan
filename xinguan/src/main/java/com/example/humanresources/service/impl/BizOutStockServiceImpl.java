package com.example.humanresources.service.impl;

import com.example.humanresources.dao.*;
import com.example.humanresources.entity.*;
import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.example.humanresources.service.BizOutStockService;
import com.example.humanresources.utils.UUIDUitl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * (BizOutStock)表服务实现类
 *
 * @author ZF
 */
@Service
@Transactional
public class BizOutStockServiceImpl implements BizOutStockService {
    @Autowired
    private BizOutStockDao outStockDao;
    @Autowired
    private BizProductStockDao productStockDao;
    @Autowired
    private BizProductDao productDao;
    @Autowired
    private BizConsumerDao consumerDao;
    @Autowired
    private BizOutStockInfoDao outStockInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BizOutStock selectById(Long id) {
        return outStockDao.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param pageNum  查询起始位置
     * @param pageSize 查询条数
     * @return 对象列表
     */
    @Override
    public Page<BizOutStock> selectAll(int pageNum, int pageSize, BizOutStock outStock) {
        PageHelper.startPage(pageNum, pageSize);
        List<BizOutStock> dataList = outStockDao.selectAllByStatus(outStock);
        PageInfo<BizOutStock> page = new PageInfo<>(dataList);
        return new Page<>(page.getTotal(), dataList);
    }

    /**
     * 新增数据
     *
     * @param bizOutStock 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(BizOutStock bizOutStock) {

        // 判断是否是新增地址
        if (bizOutStock.getConsumerId() == null) {
            BizConsumer consumer = bizOutStock.createBizConsumer();
            consumerDao.insert(consumer);
            Long id = consumer.getId();
            bizOutStock.setConsumerId(id);
        }

        Date date = new Date();
        String outNum = UUIDUitl.createUUID();
        Integer productNumber = 0;

        List<Products> products = bizOutStock.getProducts();
        for (Products product : products) {
            // 记录出库物资总数
            productNumber += product.getProductNumber();
            // 根据物资Id获取库存
            BizProductStock productStock = productDao.selectStockById(product.getProductId());
            // 库存充足
            if (productStock.getStock() >= product.getProductNumber()) {
                // 扣除库存
                productStock.setStock(productStock.getStock() - product.getProductNumber());
                productStockDao.updateById(productStock);
                // 添加出库物资关系
                BizOutStockInfo outStockInfo = new BizOutStockInfo();
                outStockInfo.setOutNum(outNum);
                outStockInfo.setPNum(productStock.getPNum());
                outStockInfo.setProductNumber(product.getProductNumber());
                outStockInfo.setCreateTime(date);
                outStockInfo.setModifiedTime(date);
                outStockInfoDao.insert(outStockInfo);
            } else {
                // 库存不足，抛异常，事务回滚
                throw new ServiceException(ErrorCodeEnum.PRODUCT_STOCK_ERROR);
            }
        }
        bizOutStock.setOutNum(outNum);
        bizOutStock.setCreateTime(date);
        bizOutStock.setStatus(2);
        bizOutStock.setProductNumber(productNumber);

        return outStockDao.insert(bizOutStock);
    }

    @Override
    public int updateStatusById(Long id, int status) {
        BizOutStock outStock = new BizOutStock();
        outStock.setStatus(status);
        outStock.setId(id);
        int code = outStockDao.updateById(outStock);
        if (code == 0) {
            throw new ServiceException(ErrorCodeEnum.PRODUCT_NOT_FOUND);
        }
        return code;
    }

    /**
     * 修改数据
     *
     * @param bizOutStock 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(BizOutStock bizOutStock) {
        return outStockDao.updateById(bizOutStock);
    }

    /**
     * 通过主键id删除数据
     *
     * @param id 主键
     */
    @Override
    public int deleteById(Long id) {
        BizOutStock outStock = outStockDao.selectById(id);
        if (outStock.getStatus() == 2) {
            List<BizProduct> products = productDao.selectProductOutDetail(outStock.getOutNum());
            for (BizProduct product : products) {
                BizProductStock productStock = productStockDao.selectByPNum(product.getPNum());
                productStock.setStock(productStock.getStock() + product.getCount());
                productStockDao.updateById(productStock);
            }
        }
        outStockInfoDao.deleteByOutNum(outStock.getOutNum());
        return outStockDao.deleteById(id);
    }

    @Override
    public BizOutStockDetail selectDetail(Long id, Integer pageNum) {
        BizOutStock outStock = outStockDao.selectById(id);
        BizOutStockDetail outStockDetail = BizOutStockDetail.create(outStock);

        BizConsumer consumer = consumerDao.selectById(outStock.getConsumerId());
        outStockDetail.setConsumerVO(consumer);

        PageHelper.startPage(pageNum, 3);
        List<BizProduct> bizProducts = productDao.selectProductOutDetail(outStock.getOutNum());
        PageInfo<BizProduct> page = new PageInfo<>(bizProducts);
        outStockDetail.setTotal(page.getTotal());
        outStockDetail.setItemVOS(bizProducts);

        return outStockDetail;
    }
}