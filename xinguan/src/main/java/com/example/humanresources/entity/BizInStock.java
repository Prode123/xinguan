package com.example.humanresources.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BizInStock implements Serializable {
    private static final long serialVersionUID = 732508545586372307L;

    private Long id;
    /**
     * 入库单编号
     */

    private String inNum;
    /**
     * 类型：1：捐赠，2：下拨，3：采购,4:退货入库
     */

    private Integer type;
    /**
     * 操作人员
     */

    private String operator;
    /**
     * 入库单创建时间
     */

    private Date createTime;
    /**
     * 入库单修改时间
     */

    private Date modified;
    /**
     * 物资总数
     */

    private Integer productNumber;
    /**
     * 来源
     */

    private Long supplierId;
    /**
     * 描述信息
     */

    private String remark;
    /**
     * 0:正常入库单,1:已进入回收,2:等待审核
     */

    private Integer status;

    /**
     * 物资Id
     */
    private List<Products> products;


    /**
     * 供应商名称
     */

    private String name;
    /**
     * 供应商地址
     */

    private String address;
    /**
     * 供应商邮箱
     */

    private String email;
    /**
     * 供应商电话
     */

    private String phone;
    /**
     * 排序
     */

    private Integer sort;
    /**
     * 联系人
     */

    private String contact;
    private String startTime;

    private String endTime;

    public BizSupplier createBizSupplier() {
        BizSupplier supplier = new BizSupplier();
        supplier.setSort(this.sort);
        supplier.setName(this.name);
        supplier.setContact(this.contact);
        supplier.setEmail(this.email);
        supplier.setPhone(this.phone);
        supplier.setAddress(this.address);
        supplier.setCreateTime(new Date());
        supplier.setModifiedTime(new Date());
        return supplier;
    }

    public void initProductNumber() {
        this.productNumber = 0;
        for (Products product : this.products) {
            this.productNumber += product.getProductNumber();
        }
    }

}