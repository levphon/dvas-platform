package com.glsx.vasp.business.modules.coupon.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.coupon.entity.CouponRecipients;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 由（平台）派发/（用户）领用优惠券表
 * 
 * @author liumh
 * @date 2019-04-04 18:47:32
 */
@Component
public interface CouponRecipientsMapper extends BaseMapper<CouponRecipients> {

    @DataPermAspect(showSql = true, tablePerix = "")
    List<CouponRecipients> search(Map<String,Object> params);
}
