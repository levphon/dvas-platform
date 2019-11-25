package com.glsx.vasp.business.modules.coupon.model;

import com.glsx.vasp.business.modules.coupon.entity.Coupon;

/**
 * 优惠券商品
 * @author liumh
 * @date 2019-04-04 17:23:51
 */
public class CouponModel{

    private String name;

    private Coupon coupon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
