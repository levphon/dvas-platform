package com.glsx.vasp.business.modules.coupon.service;

import com.glsx.vasp.business.modules.coupon.entity.StoreCouponConfig;
import com.glsx.vasp.business.modules.coupon.mapper.StoreCouponConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreCouponConfigService{
    @Autowired
    StoreCouponConfigMapper storeCouponConfigMapper;

    public StoreCouponConfig selectByPrimaryKey(Integer id) {
        return  storeCouponConfigMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StoreCouponConfig StoreCouponConfig) {
        return  storeCouponConfigMapper.updateByPrimaryKeySelective(StoreCouponConfig);
    }

    public int save(StoreCouponConfig StoreCouponConfig) {
        return  storeCouponConfigMapper.insertSelective(StoreCouponConfig);
    }
}
