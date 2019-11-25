package com.glsx.vasp.admin.modules.coupon.mapper;

import com.glsx.vasp.admin.modules.coupon.entity.Coupon;
import com.glsx.vasp.admin.modules.coupon.model.CouponExportModel;
import com.glsx.vasp.admin.modules.coupon.model.CouponGoodsVO;
import com.glsx.vasp.admin.modules.coupon.model.CouponStoreVO;
import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CouponMapper extends BaseMapper<Coupon> {

    @DataPermAspect(showSql = true, tablePerix = "dc")
    List<CouponGoodsVO> selectListByGoodsId(Map<String, Object> params);

    @DataPermAspect(showSql = true, tablePerix = "dc")
    List<CouponStoreVO> search(Map<String, Object> params);

    @DataPermAspect(showSql = true, tablePerix = "cnt_table")
    Long searchCount(Map<String, Object> params);

    @DataPermAspect(showSql = true, tablePerix = "dc")
    List<CouponExportModel> export(Map<String, Object> params);

    List<CouponStoreVO> selectByStoreId(@Param("storeId") Integer storeId);

    @DataPermAspect(showSql = true, tablePerix = "dc")
    List<Coupon> selectUnconfigedByCouponInfo(Coupon coupon);

    @DataPermAspect(showSql = true, tablePerix = "dc")
    List<Coupon> selectConfigedByCouponInfo(Coupon coupon);

    List<Coupon> selectByConfigedCouponsByStoreId(Integer storeId);

    Coupon selectCouponByStoreIdAndGoods(@Param("storeId") Integer storeId, @Param("goodsId") Integer goodsId, @Param("threshold") Integer threshold);

    int logicDelete(Integer couponId);


}