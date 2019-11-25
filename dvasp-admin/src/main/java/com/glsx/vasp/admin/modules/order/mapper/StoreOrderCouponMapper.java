package com.glsx.vasp.admin.modules.order.mapper;

import com.glsx.vasp.admin.modules.order.entity.StoreOrderCoupon;
import com.glsx.vasp.admin.modules.coupon.model.CouponRecipientsModel;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;

public interface StoreOrderCouponMapper extends BaseMapper<StoreOrderCoupon> {

    List<StoreOrderCoupon> selectCouponListByOrderNo(String orderNo);

    List<CouponRecipientsModel> selectCouponRecipientsListByOrderNo(String orderNo);

}