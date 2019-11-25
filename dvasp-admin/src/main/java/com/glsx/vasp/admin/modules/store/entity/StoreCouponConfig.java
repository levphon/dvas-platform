package com.glsx.vasp.admin.modules.store.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "d_store_coupon_config")
public class StoreCouponConfig extends BaseEntity {

    /**
     * 优惠券id
     */
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 门店id
     */
    @Column(name = "store_id")
    private Integer storeId;

    /**
     * 获取优惠券id
     *
     * @return coupon_id - 优惠券id
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * 设置优惠券id
     *
     * @param couponId 优惠券id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取门店id
     *
     * @return store_id - 门店id
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * 设置门店id
     *
     * @param storeId 门店id
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

}