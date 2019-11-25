package com.glsx.vasp.business.modules.coupon.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.coupon.entity.Coupon;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 优惠券表
 * @author liumh
 * @date 2019-04-04 17:23:51
 */
@Component
public interface CouponMapper extends BaseMapper<Coupon> {

    @DataPermAspect(showSql = true, tablePerix = "dc")
    List<Coupon> selectCouponByStoreId(@Param("storeId")Integer storeId);
}
