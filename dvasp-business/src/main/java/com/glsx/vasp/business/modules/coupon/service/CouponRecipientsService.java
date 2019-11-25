package com.glsx.vasp.business.modules.coupon.service;

import com.glsx.vasp.business.common.utils.UserUtils;
import com.glsx.vasp.business.modules.coupon.entity.CouponRecipients;
import com.glsx.vasp.business.modules.coupon.mapper.CouponRecipientsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CouponRecipientsService {
    @Autowired
    CouponRecipientsMapper couponRecipientsMapper;

    @Autowired
    UserUtils userUtils;

    public int saveOrUpdate(CouponRecipients couponRecipients) {
        if (couponRecipients.getId() != null) return couponRecipientsMapper.updateByPrimaryKeySelective(couponRecipients);
        return couponRecipientsMapper.insertSelective(couponRecipients);
    }

    public List<CouponRecipients> search(Map<String, Object> params) {
        return couponRecipientsMapper.search(params);
    }

    public CouponRecipients selectByPrimaryKey(Integer id) {
        return couponRecipientsMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(CouponRecipients couponRecipients) {
        return couponRecipientsMapper.updateByPrimaryKeySelective(couponRecipients);
    }
}
