package com.glsx.vasp.business.modules.order.service;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.utils.UserUtils;
import com.glsx.vasp.business.modules.coupon.entity.CouponRecipients;
import com.glsx.vasp.business.modules.coupon.service.CouponRecipientsService;
import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.business.modules.goods.service.GoodsService;
import com.glsx.vasp.business.modules.order.entity.StoreOrder;
import com.glsx.vasp.business.modules.order.mapper.StoreOrderMapper;
import com.glsx.vasp.business.modules.order.model.OrderGoodsModel;
import com.glsx.vasp.business.modules.order.model.StoreOrderModel;
import com.glsx.vasp.business.modules.shopcar.entity.ShopcarDetail;
import com.glsx.vasp.business.modules.shopcar.service.ShopcarService;
import com.glsx.vasp.business.modules.storeGoods.entity.StoreGoodsConfig;
import com.glsx.vasp.business.modules.storeGoods.service.StoreGoodsConfigService;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 门店订单表
 *
 * @author liumh
 * @date 2019-04-09 14:21:28
 */
@Service
public class StoreOrderService {
    @Autowired
    ShopcarService shopcarService;
    @Autowired
    CouponRecipientsService couponRecipientsService;
    @Autowired
    StoreOrderMapper storeOrderMapper;
    @Autowired
    UserUtils userUtils;
    @Autowired
    StoreGoodsConfigService storeGoodsConfigService;
    @Autowired
    GoodsService goodsService;

    private static final Logger logger = LoggerFactory.getLogger(StoreOrderService.class);

    public void saveOrder(StoreOrder storeOrder, List<OrderGoodsModel> orderGoodsModel, List<Integer> goodsIds) {
        Map<String, Object> calcMap = calculationOrder(storeOrder, orderGoodsModel, goodsIds);
        storeOrder.setDiscountAmount((BigDecimal) calcMap.get("discountAmount"));
        storeOrder.setPayAmount((BigDecimal) calcMap.get("payAmount"));
        storeOrder.setProfit((BigDecimal) calcMap.get("profit"));
        storeOrder.setCreateTime(new Date());
        logger.info(storeOrder.toString());
        storeOrderMapper.insertSelective(storeOrder);
    }

    //计算商品到店价总额,查询订单下所有商品
    private Map calculationOrder(StoreOrder storeOrder, List<OrderGoodsModel> orderGoodsModel, List<Integer> goodsIds) {
        Map<String, Object> calcMap = new HashMap<String, Object>();
        BigDecimal shopAmount = BigDecimal.ZERO;//商品到店总额
        BigDecimal costAmount = BigDecimal.ZERO;//商品成本总额
        //计算订单总额,查询订单下所有商品
        Map<String, Object> params = new HashMap<>();
        params.put("userId", storeOrder.getUserId());
        params.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        Map<Integer, Integer> orderGoodsMap = new HashMap<>();
        StringBuffer goodsBuffer = new StringBuffer();
        for (OrderGoodsModel goodsModel : orderGoodsModel) {
            orderGoodsMap.put(goodsModel.getGoodsId(), goodsModel.getGoodsNum());
            Goods goods = goodsService.getById(goodsModel.getGoodsId());
            //查看门店是否设置商品到店价格
            if (userUtils.getSessionUser().getStoreId() != null) {
                Map<String, Object> configParams = new HashMap<>();
                //configParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
                configParams.put("goodsId", goodsModel.getGoodsId());
                configParams.put("storeId", userUtils.getSessionUser().getStoreId());
                List<StoreGoodsConfig> storeGoodsList = storeGoodsConfigService.search(configParams);
                if (CollectionUtils.isNotEmpty(storeGoodsList))
                    goods.setShopPrice(storeGoodsList.get(0).getShopPrice());
            }
            String shopInfo = String.format("您购买的商品%s到店价格为%s,数量%s,", goods.getName(), goods.getShopPrice(), goodsModel.getGoodsNum());
            goodsBuffer.append(shopInfo);
            shopAmount = shopAmount.add(goods.getShopPrice().multiply(new BigDecimal(goodsModel.getGoodsNum())));
            String costInfo = String.format("成本价格为%s,数量%s。", goods.getCostPrice(), goodsModel.getGoodsNum());
            goodsBuffer.append(costInfo);
            costAmount = costAmount.add(goods.getCostPrice().multiply(new BigDecimal(goodsModel.getGoodsNum())));
        }
        logger.info(goodsBuffer.toString());

        calcMap.put("shopAmount", shopAmount);//商品到店总额
        calcMap.put("costAmount", costAmount);//商品成本总额
        BigDecimal discountAmount = BigDecimal.ZERO;//订单优惠总金额
        BigDecimal couponAmount = BigDecimal.ZERO;//优惠券成本总金额
        Map<String, Object> discountParams = new HashMap<>();
        discountParams.put("userId", storeOrder.getUserId());
        discountParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        discountParams.put("usedFlag", Constants.NOT_USED);//未使用
        discountParams.put("goodsIds", goodsIds);
        List<CouponRecipients> list = couponRecipientsService.search(discountParams);
        StringBuffer couponBuffer = new StringBuffer();
        if (CollectionUtils.isNotEmpty(list)) {
            for (CouponRecipients couponRecipients : list) {
                Integer goodsNum = orderGoodsMap.get(couponRecipients.getGoodsId());
                String discountInfo = String.format("您使用了优惠券%s,抵用价%s,数量%s", couponRecipients.getCouponId(), couponRecipients.getUsedAmount(), couponRecipients.getUsedQuantity() * goodsNum);
                couponBuffer.append(discountInfo);
                discountAmount = discountAmount.add(new BigDecimal(couponRecipients.getUsedQuantity()).multiply(couponRecipients.getUsedAmount()).multiply(new BigDecimal(goodsNum)));
                String couponInfo = String.format("成本价%s,数量%s", couponRecipients.getCostAmount(), couponRecipients.getUsedQuantity() * goodsNum);
                couponBuffer.append(couponInfo);
                couponAmount = couponAmount.add(new BigDecimal(couponRecipients.getUsedQuantity()).multiply(couponRecipients.getCostAmount()).multiply(new BigDecimal(goodsNum)));
            }
        }
        logger.info(couponBuffer.toString());
        calcMap.put("discountAmount", discountAmount);//优惠券抵用总额
        calcMap.put("payAmount", shopAmount.subtract(discountAmount));//订单支付总金额=商品到店总额-优惠券抵用总额
        calcMap.put("profit", shopAmount.subtract(discountAmount).subtract(costAmount).add(couponAmount));//利润=商品到店总额-优惠券抵用总额-商品成本总额+优惠券成本总额
        return calcMap;
    }

    public List<StoreOrderModel> searchStoreOrderMode(Map<String, Object> params) {
        return storeOrderMapper.searchStoreOrderMode(params);
    }

    public Integer delete(Integer id) {
        StoreOrder storeOrder = storeOrderMapper.selectByPrimaryKey(id);
        storeOrder.setDelFlag(Constants.IS_DEL);
        return storeOrderMapper.updateByPrimaryKeySelective(storeOrder);
    }
}

