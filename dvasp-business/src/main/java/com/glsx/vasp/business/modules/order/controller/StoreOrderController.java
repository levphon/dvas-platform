package com.glsx.vasp.business.modules.order.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.modules.AbstractController;
import com.glsx.vasp.business.modules.common.service.CommonService;
import com.glsx.vasp.business.modules.coupon.entity.CouponRecipients;
import com.glsx.vasp.business.modules.coupon.service.CouponRecipientsService;
import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.business.modules.goods.service.GoodsService;
import com.glsx.vasp.business.modules.order.entity.StoreOrder;
import com.glsx.vasp.business.modules.order.entity.StoreOrderGoods;
import com.glsx.vasp.business.modules.order.model.OrderGoodsDetailModel;
import com.glsx.vasp.business.modules.order.model.OrderGoodsModel;
import com.glsx.vasp.business.modules.order.model.StoreOrderModel;
import com.glsx.vasp.business.modules.order.service.StoreOrderCouponsService;
import com.glsx.vasp.business.modules.order.service.StoreOrderGoodsService;
import com.glsx.vasp.business.modules.order.service.StoreOrderService;
import com.glsx.vasp.business.modules.shopcar.entity.Shopcar;
import com.glsx.vasp.business.modules.shopcar.service.ShopcarService;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.business.modules.store.service.StoreService;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 门店订单表
 *
 * @author liumh
 * @date 2019-04-09 14:21:28
 */
@RestController
@RequestMapping("/storeOrder")
public class StoreOrderController extends AbstractController {
    @Autowired
    private StoreOrderService storeOrderService;

    @Autowired
    StoreOrderCouponsService storeOrderCouponsService;

    @Autowired
    StoreOrderGoodsService storeOrderGoodsService;

    @Autowired
    CommonService commonService;

    @Autowired
    CouponRecipientsService couponRecipientsService;

    @Autowired
    ShopcarService shopcarService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    StoreService storeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(Pagination pagination, Integer status) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserId());
        params.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        params.put("status", status);
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        List<StoreOrderModel> list = storeOrderService.searchStoreOrderMode(params);
        long total = ((Page<StoreOrderModel>) list).getTotal();
        return R.ok().putPageData(list, total);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        //StoreOrder storeOrder = storeOrderService.getById(id);

        return R.ok().put("storeOrder", "");
    }

    /**
     * 商品下单
     */
    @RequestMapping("/saveOrder")
    @Transactional
    public R saveOrder(@RequestBody(required = false) List<OrderGoodsModel> orderGoodsModel) {
        StoreUser sessionUser = getSessionUser();
        if (sessionUser == null || sessionUser.getStoreId() == null)
            return R.error(ResultCodeEnum.STORE_NOT_EXIST.getCode(), ResultCodeEnum.STORE_NOT_EXIST.getMsg());
        Store store = storeService.selectByPrimaryKey(sessionUser.getStoreId());
        if (store.getDelFlag() == Constants.IS_DEL)
            return R.error(ResultCodeEnum.STORE_NOT_EXIST.getCode(), ResultCodeEnum.STORE_NOT_EXIST.getMsg());
        if (store.getEnableStatus() == Constants.ENABLE_STATUS_CHECK)
            return R.error(ResultCodeEnum.STORE_IS_CHECK.getCode(), ResultCodeEnum.STORE_IS_CHECK.getMsg());
        if (store.getEnableStatus() == Constants.ENABLE_STATUS_INVALID)
            return R.error(ResultCodeEnum.STORE_IS_STOP.getCode(), ResultCodeEnum.STORE_IS_STOP.getMsg());
        if (store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
            List<Integer> goodsIds = new ArrayList<>();
            for (OrderGoodsModel goodsModel : orderGoodsModel) {
                Goods goods = goodsService.getById(goodsModel.getGoodsId());
                if (goods == null || goods.getDelFlag() == Constants.IS_DEL || goods.getEnableStatus() == Constants.ENABLE_STATUS_INVALID) {
                    return R.error(String.format("%s该商品已下架，请删除购物车", goodsModel.getName()));
                }
                //清空购物车
                Map<String, Object> shopParams = new HashMap<>();
                shopParams.put("userId", getUserId());
                shopParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
                shopParams.put("goodsId", goodsModel.getGoodsId());
                List<Shopcar> shopcarList = shopcarService.search(shopParams);
                if (CollectionUtils.isNotEmpty(shopcarList)) {
                    Shopcar shopcar = shopcarList.get(0);
                    shopcar.setGoodsNum(goodsModel.getGoodsNum());
                    shopcar.setDelFlag(Constants.IS_DEL);
                    shopcarService.updateById(shopcar);
                }
                goodsIds.add(goodsModel.getGoodsId());
            }

            String orderNo = commonService.getContractSerialNum(Constants.USER_ORDER_NO_SEQUENCE_NAME);
            StoreOrder storeOrder = new StoreOrder();
            storeOrder.setOrderNo(orderNo);
            storeOrder.setUserId(getUserId());
            storeOrder.setDelFlag(Constants.NOT_DEL);//删除标记  -1：已删除  0：正常
            storeOrder.setEnableStatus(Constants.ENABLE_STATUS_EFFECT);//启用状态 1:启用,2:停用
            storeOrder.setStatus(Constants.GOODS_STATUS_ORDER);//订单状态：1已下单，2已安装

            //保存门店订单表数据
            storeOrderService.saveOrder(storeOrder, orderGoodsModel, goodsIds);
            //保存订单用券关系表数据
            storeOrderCouponsService.orderCoupons(storeOrder.getOrderNo(), goodsIds);
            //保存订单商品关系表
            storeOrderGoodsService.orderGoods(storeOrder.getOrderNo(), orderGoodsModel);
            //所有的优惠券变更已使用，下次可以重新领取优惠券
            Map<String, Object> params = new HashMap<>();
            params.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
            params.put("usedFlag", Constants.NOT_USED);//未使用
            params.put("userId", getUserId());
            List<CouponRecipients> list = couponRecipientsService.search(params);
            if (CollectionUtils.isNotEmpty(list)) {
                for (CouponRecipients couponRecipients : list) {
                    couponRecipients.setUsedFlag(Constants.IS_USED);
                    couponRecipientsService.updateByPrimaryKeySelective(couponRecipients);
                }
            }
        }
        return R.ok();
}

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StoreOrder storeOrder) {
        //ValidatorUtils.validateEntity(storeOrder);
        //storeOrderService.updateById(storeOrder);

        return R.ok();
    }

    /**
     * 删除订单
     */
    @RequestMapping("/delete")
    public R delete(Integer id) {
        storeOrderService.delete(id);
        return R.ok();
    }

}
