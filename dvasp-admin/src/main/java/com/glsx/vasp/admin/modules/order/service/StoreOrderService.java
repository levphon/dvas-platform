package com.glsx.vasp.admin.modules.order.service;

import com.glsx.vasp.admin.modules.coupon.model.CouponRecipientsModel;
import com.glsx.vasp.admin.modules.order.entity.StoreOrder;
import com.glsx.vasp.admin.modules.order.entity.StoreOrderCoupon;
import com.glsx.vasp.admin.modules.order.entity.StoreOrderGoods;
import com.glsx.vasp.admin.modules.order.mapper.StoreOrderCouponMapper;
import com.glsx.vasp.admin.modules.order.mapper.StoreOrderGoodsMapper;
import com.glsx.vasp.admin.modules.order.mapper.StoreOrderMapper;
import com.glsx.vasp.admin.modules.order.model.StoreOrderExportModel;
import com.glsx.vasp.admin.modules.store.entity.Store;
import com.glsx.vasp.admin.modules.store.entity.StoreUser;
import com.glsx.vasp.admin.modules.store.mapper.StoreMapper;
import com.glsx.vasp.admin.modules.store.mapper.StoreUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/3 19:00
 * @since 1.0.0
 */
@Service
public class StoreOrderService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StoreOrderMapper storeOrderMapper;

    @Autowired
    private StoreUserMapper storeUserMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreOrderGoodsMapper storeOrderGoodsMapper;

    @Autowired
    private StoreOrderCouponMapper storeOrderCouponMapper;

    public List<StoreOrder> search(Map<String, Object> params) {
        return storeOrderMapper.search(params);
    }

    public List<StoreOrderExportModel> export(Map<String, Object> params) {
        return storeOrderMapper.export(params);
    }

    public StoreOrder getById(Integer orderId) {
        return storeOrderMapper.selectByPrimaryKey(orderId);
    }

    public List<StoreOrderGoods> getGoodsListByOrderNo(String orderNo) {
        return storeOrderGoodsMapper.selectGoodsListByOrderNo(orderNo);
    }

    public List<StoreOrderCoupon> getCouponListByOrderNo(String orderNo) {
        return storeOrderCouponMapper.selectCouponListByOrderNo(orderNo);
    }

    public List<CouponRecipientsModel> getCouponRecipientsListByOrderNo(String orderNo) {
        return storeOrderCouponMapper.selectCouponRecipientsListByOrderNo(orderNo);
    }

    public StoreOrder getStoreOrderInfo(Integer orderId) {
        StoreOrder storeOrder = storeOrderMapper.selectByPrimaryKey(orderId);
        StoreUser storeUser = storeUserMapper.selectByPrimaryKey(storeOrder.getUserId());
        Store store = storeMapper.selectByPrimaryKey(storeUser.getStoreId());
        storeOrder.setStoreName(store.getName());
        //订单商品
        List<StoreOrderGoods> goodsList = this.getGoodsListByOrderNo(storeOrder.getOrderNo());
        //订单用券
        List<CouponRecipientsModel> couponList = this.getCouponRecipientsListByOrderNo(storeOrder.getOrderNo());
        for (StoreOrderGoods sog : goodsList) {
            //默认支付总额和商品利润
            //商品实际支付总额=商品到店价总额
            sog.setPayGoodsAmount(sog.getTotalShopPrice());
            //商品利润=商品实际支付总额-商品成本价总额
            sog.setProfit(sog.getTotalShopPrice().subtract(sog.getTotalCostPrice()));
            //券支付总额=用券成本总额=单张券成本金额*用券总数量
            sog.setPayCouponAmount(BigDecimal.ZERO);

            for (CouponRecipientsModel crm : couponList) {
                //用券的情况下
                if (sog.getGoodsId().equals(crm.getGoodsId())) {
                    //用券总数量=商品数量*单张券抵用数量
                    sog.setUsedCouponQuantity(sog.getGoodsNum() * crm.getUsedQuantity());

                    //券支付总额=用券成本总额=单张券成本金额*用券总数量
                    sog.setPayCouponAmount(crm.getCostAmount().multiply(new BigDecimal(sog.getUsedCouponQuantity())));

                    //商品优惠总金额=券抵用总额=单张券抵用金额*用券总数量
                    BigDecimal couponUsedAmount = crm.getUsedAmount().multiply(new BigDecimal(sog.getUsedCouponQuantity()));

                    //商品实际支付总额=商品到店价总额-商品优惠总金额
                    sog.setPayGoodsAmount(sog.getTotalShopPrice().subtract(couponUsedAmount));

                    //商品利润=商品实际支付总额-商品成本价总额+券实际支付总额=商品到店价总额-商品成本价总额-商品优惠总金额+券支付总额
                    sog.setProfit(sog.getPayGoodsAmount().subtract(sog.getTotalCostPrice()).add(sog.getPayCouponAmount()));

                    //用券信息
                    sog.setCoupons(crm);
                    break;
                }
            }
        }
        storeOrder.setGoodsList(goodsList);
        return storeOrder;
    }

}
