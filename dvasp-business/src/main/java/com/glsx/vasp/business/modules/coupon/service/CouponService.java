package com.glsx.vasp.business.modules.coupon.service;

import com.glsx.vasp.business.modules.coupon.entity.Coupon;
import com.glsx.vasp.business.modules.coupon.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CouponService {


    @Autowired
    CouponMapper couponMapper;

    public List<Coupon> selectCouponByStoreId(Integer storeId) {

        return couponMapper.selectCouponByStoreId(storeId);
    }
    public Coupon selectByPrimaryKey(Integer id){
        return couponMapper.selectByPrimaryKey(id);
    }
}
