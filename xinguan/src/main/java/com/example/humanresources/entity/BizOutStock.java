package com.example.humanresources.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BizOutStock implements Serializable {
    private static final long serialVersionUID = -26476006253492093L;

    private Long id;
    /**
     * 出库单
     */

    private String outNum;
    /**
     * 出库类型:0:直接出库,1:审核出库
     */

    private Integer type;
    /**
     * 操作人
     */

    private String operator;
    /**
     * 出库时间
     */

    private Date createTime;
    /**
     * 出库总数
     */

    private Integer productNumber;
    /**
     * 消费者id
     */

    private Long consumerId;
    /**
     * 备注
     */

    private String remark;
    /**
     * 状态:0:正常入库,1:已进入回收,2:等待审核
     */

    private Integer status;
    /**
     * 紧急程度:1:不急,2:常规,3:紧急4:特急
     */

    private Integer priority;

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

    public BizConsumer createBizConsumer() {
        BizConsumer consumer = new BizConsumer();
        consumer.setSort(this.sort);
        consumer.setName(this.name);
        consumer.setContact(this.contact);
        consumer.setPhone(this.phone);
        consumer.setAddress(this.address);
        consumer.setCreateTime(new Date());
        consumer.setModifiedTime(new Date());
        return consumer;
    }

}