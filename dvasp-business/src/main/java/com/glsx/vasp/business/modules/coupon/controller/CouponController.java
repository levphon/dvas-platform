package com.glsx.vasp.business.modules.coupon.controller;

import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.AbstractController;
import com.glsx.vasp.business.modules.coupon.entity.Coupon;
import com.glsx.vasp.business.modules.coupon.model.CouponModel;
import com.glsx.vasp.business.modules.coupon.service.CouponService;
import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.business.modules.goods.service.GoodsService;
import com.glsx.vasp.business.modules.shopcar.model.ShopcarModel;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.business.modules.store.service.StoreService;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 优惠券表
 *
 * @author liumh
 * @date 2019-04-04 17:23:51
 */
@RestController
@RequestMapping("/coupon")
public class CouponController extends AbstractController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private GoodsService goodsService;


    /**
     * 门店优惠券列表
     */
    @AuthCheck
    @RequestMapping("/couponList")
    public R storeCouponList(@RequestBody(required = false) List<ShopcarModel> list) {
        List<CouponModel> couponModelList = new ArrayList<>();
        //查询门店审核状态
        Store store = storeService.selectByPrimaryKey(getStoreId());
        if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
            List<Coupon> couponList = couponService.selectCouponByStoreId(getStoreId());
            if (CollectionUtils.isNotEmpty(couponList)) {
                for (Coupon coupon : couponList) {
                    if (CollectionUtils.isNotEmpty(list)) {
                        for (ShopcarModel shopcarModel : list) {
                            if (shopcarModel.getGoodsId() == Integer.parseInt(coupon.getWithSn())) {
                                CouponModel couponModel = new CouponModel();
                                couponModel.setCoupon(coupon);
                                couponModel.setName(shopcarModel.getName());
                                couponModelList.add(couponModel);
                            }
                        }
                    }
                }
            }
        }
        return R.ok().put("result", couponModelList);
    }

    /**
     * 门店优惠券列表
     */
    @RequestMapping("/list")
    public R list() {
        List<CouponModel> couponModelList = new ArrayList<>();
        //查询门店审核状态
        Store store = storeService.selectByPrimaryKey(getStoreId());
        if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
            List<Coupon> couponList = couponService.selectCouponByStoreId(getStoreId());
            if (CollectionUtils.isNotEmpty(couponList)) {
                for (Coupon coupon : couponList) {
                    Goods goods = goodsService.getById(Integer.parseInt(coupon.getWithSn()));
                    if (goods.getDelFlag() == Constants.NOT_DEL && goods.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
                        CouponModel couponModel = new CouponModel();
                        couponModel.setCoupon(coupon);
                        couponModel.setName(goods.getName());
                        couponModelList.add(couponModel);
                    }
                }
            }
        }
        return R.ok().put("result", couponModelList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        //DCouponEntity dCoupon = dCouponService.getById(id);

        return R.ok().put("dCoupon", "");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Coupon dCoupon) {
        //dCouponService.save(dCoupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Coupon dCoupon) {
        //ValidatorUtils.validateEntity(dCoupon);
        //dCouponService.updateById(dCoupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        //dCouponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
