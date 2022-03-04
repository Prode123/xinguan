package com.example.humanresources.entity;

import com.example.humanresources.exception.ErrorCodeEnum;
import com.example.humanresources.exception.ServiceException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zpy
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BizProduct implements Serializable {
    private static final long serialVersionUID = -47274499455323564L;

    private Long id;
    /**
     * 商品编号
     */

    private String pNum;
    /**
     * 商品名称
     */

    private String name;
    /**
     * 图片
     */

    private String imageUrl;
    /**
     * 规格型号
     */

    private String model;
    /**
     * 计算单位
     */

    private String unit;
    /**
     * 备注
     */

    private String remark;
    /**
     * 排序
     */

    private Integer sort;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 修改时间
     */

    private Date modifiedTime;

    /**
     * 分类列表
     */
    private List<Long> categorys;

    /**
     * 1级分类
     */

    private Long oneCategoryId;
    /**
     * 2级分类
     */

    private Long twoCategoryId;
    /**
     * 3级分类
     */

    private Long threeCategoryId;
    /**
     * 是否删除:1物资正常,0:物资回收,2:物资审核中
     */

    private Integer status;

    /**
     * 单次入库数量
     */
    private Integer count;

    /**
     * 库存数量
     */
    private Long stock;

    public void initCategory() {
        if (categorys == null) {
            return;
        }
        if (categorys.size() != 3) {
            throw new ServiceException(ErrorCodeEnum.PRODUCT_CATEGORY_ERROR);
        }
        this.oneCategoryId = categorys.get(0);
        this.twoCategoryId = categorys.get(1);
        this.threeCategoryId = categorys.get(2);

    }

}