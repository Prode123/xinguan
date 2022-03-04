package com.example.humanresources.entity;

import lombok.Data;

import java.util.List;

/**
 * @author zpy
 * @since 2021/12/09
 */
@Data
public class BizOutStockDetail {

    private String outNum;
    private String operator;
    private Integer status;
    private Integer type;

    private BizConsumer consumerVO;
    private Long total;

    private List<BizProduct> itemVOS;

    public static BizOutStockDetail create(BizOutStock outStock) {
        BizOutStockDetail outStockDetail = new BizOutStockDetail();
        outStockDetail.setOutNum(outStock.getOutNum());
        outStockDetail.setOperator(outStock.getOperator());
        outStockDetail.setStatus(outStock.getStatus());
        outStockDetail.setType(outStock.getType());

        return outStockDetail;
    }
}
