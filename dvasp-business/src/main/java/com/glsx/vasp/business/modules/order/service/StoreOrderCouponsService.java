package com.glsx.vasp.business.modules.order.service;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.utils.UserUtils;
import com.glsx.vasp.business.modules.coupon.entity.CouponRecipients;
import com.glsx.vasp.business.modules.coupon.service.CouponRecipientsService;
import com.glsx.vasp.business.modules.order.entity.StoreOrder;
import com.glsx.vasp.business.modules.order.entity.StoreOrderCoupons;
import com.glsx.vasp.business.modules.order.mapper.StoreOrderCouponsMapper;
import com.glsx.vasp.business.modules.order.model.OrderGoodsModel;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单用券关系表
 *
 * @author liumh
 * @date 2019-04-09 14:52:40
 */
@Service
public class StoreOrderCouponsService  {

    @Autowired
    CouponRecipientsService couponRecipientsService;
    @Autowired
    StoreOrderCouponsMapper storeOrderCouponsMapper;

    @Autowired
    UserUtils userUtils;

    public void orderCoupons(String orderNo,List<Integer> goodsIds) {
        Map<String, Object> params = new HashMap<>();
        params.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        params.put("usedFlag", Constants.NOT_USED);//未使用
        params.put("goodsIds",goodsIds);
        params.put("userId", userUtils.getSessionUser().getId());
        List<CouponRecipients> list = couponRecipientsService.search(params);
        if (CollectionUtils.isNotEmpty(list)){
            for (CouponRecipients couponRecipients:list) {
                StoreOrderCoupons storeOrderCoupons = new StoreOrderCoupons();
                storeOrderCoupons.setCouponRecipientsId(couponRecipients.getId());
                storeOrderCoupons.setOrderNo(orderNo);
                storeOrderCouponsMapper.insertSelective(storeOrderCoupons);
            }
        }
    }
}

