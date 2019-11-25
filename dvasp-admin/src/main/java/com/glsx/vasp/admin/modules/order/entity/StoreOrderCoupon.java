package com.glsx.vasp.admin.modules.order.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "d_store_order_coupons")
public class StoreOrderCoupon implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 订单流水
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 用券id
     */
    @Column(name = "coupon_recipients_id")
    private Integer couponRecipientsId;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单流水
     *
     * @return order_no - 订单流水
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单流水
     *
     * @param orderNo 订单流水
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取用券id
     *
     * @return coupon_recipients_id - 用券id
     */
    public Integer getCouponRecipientsId() {
        return couponRecipientsId;
    }

    /**
     * 设置用券id
     *
     * @param couponRecipientsId 用券id
     */
    public void setCouponRecipientsId(Integer couponRecipientsId) {
        this.couponRecipientsId = couponRecipientsId;
    }
}