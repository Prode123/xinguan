package com.example.humanresources.entity;

import lombok.Data;

import java.util.List;

/**
 * @author zpy
 * @since 2021/12/08
 */
@Data
public class BizInStockDetail {

    private String inNum;
    private String operator;
    private Integer status;
    private Integer type;

    private BizSupplier supplierVO;
    private Long total;

    private List<BizProduct> itemVOS;

    public static BizInStockDetail create(BizInStock inStock) {
        BizInStockDetail inStockDetail = new BizInStockDetail();
        inStockDetail.setInNum(inStock.getInNum());
        inStockDetail.setOperator(inStock.getOperator());
        inStockDetail.setStatus(inStock.getStatus());
        inStockDetail.setType(inStock.getType());

        return inStockDetail;
    }
}
