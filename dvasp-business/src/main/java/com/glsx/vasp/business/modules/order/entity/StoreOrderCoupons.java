package com.glsx.vasp.business.modules.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 订单用券关系表
 *
 * @author liumh
 * @date 2019-04-09 14:52:40
 */
@Entity
@Table(name = "d_store_order_coupons")
public class StoreOrderCoupons implements Serializable {

    private static final long serialVersionUID = -3607154733675261835L;
    /**
     * 主键
     */
    @Column(name = "id")
    private Integer id;
    /**
     * 订单流水
     */
    @Column(name = "order_no",length = 32)
    private String orderNo;
    /**
     * 用券id
     */
    @Column(name = "coupon_recipients_id")
    private Integer couponRecipientsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCouponRecipientsId() {
        return couponRecipientsId;
    }

    public void setCouponRecipientsId(Integer couponRecipientsId) {
        this.couponRecipientsId = couponRecipientsId;
    }
}
