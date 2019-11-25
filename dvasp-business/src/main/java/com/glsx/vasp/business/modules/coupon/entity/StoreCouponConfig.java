package com.glsx.vasp.business.modules.coupon.entity;

import com.glsx.vasp.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * (由平台分配用户)分配优惠券表
 * 
 * @author liumh
 * @date 2019-04-04 18:36:00
 */

public class StoreCouponConfig extends BaseEntity {
	private static final long serialVersionUID = 7492723751465591780L;
	/**
	 * 优惠券id
	 */
	private Integer couponId;
	/**
	 * 门店id
	 */
	private Integer storeId;

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
}
