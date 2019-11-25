package com.glsx.vasp.admin.modules.store.mapper;

import com.glsx.vasp.admin.modules.store.entity.StoreCouponConfig;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface StoreCouponConfigMapper extends BaseMapper<StoreCouponConfig> {

    StoreCouponConfig selectByStoreIdAndCouponId(@Param("storeId") Integer storeId, @Param("couponId") Integer couponId);

    int deleteByCouponId(@Param("couponId") Integer couponId);

    int deleteSCRelation(@Param("scId") Integer scId);

}