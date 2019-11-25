package com.glsx.vasp.business.modules.order.service;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.utils.UserUtils;
import com.glsx.vasp.business.modules.coupon.entity.CouponRecipients;
import com.glsx.vasp.business.modules.coupon.service.CouponRecipientsService;
import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.business.modules.goods.service.GoodsService;
import com.glsx.vasp.business.modules.order.entity.StoreOrder;
import com.glsx.vasp.business.modules.order.entity.StoreOrderGoods;
import com.glsx.vasp.business.modules.order.mapper.StoreOrderGoodsMapper;
import com.glsx.vasp.business.modules.order.model.OrderGoodsDetailModel;
import com.glsx.vasp.business.modules.order.model.OrderGoodsModel;
import com.glsx.vasp.business.modules.order.model.StoreOrderGoodsModel;
import com.glsx.vasp.business.modules.shopcar.entity.Shopcar;
import com.glsx.vasp.business.modules.shopcar.entity.ShopcarDetail;
import com.glsx.vasp.business.modules.shopcar.service.ShopcarService;
import com.glsx.vasp.business.modules.storeGoods.entity.StoreGoodsConfig;
import com.glsx.vasp.business.modules.storeGoods.service.StoreGoodsConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单商品关系表
 *
 * @author liumh
 * @date 2019-04-09 15:03:07
 */
@Service
public class StoreOrderGoodsService {

    @Autowired
    ShopcarService shopcarService;
    @Autowired
    UserUtils userUtils;
    @Autowired
    CouponRecipientsService couponRecipientsService;
    @Autowired
    StoreOrderGoodsMapper storeOrderGoodsMapper;
    @Autowired
    GoodsService goodsService;
    @Autowired
    StoreGoodsConfigService storeGoodsConfigService;

    public void orderGoods(String orderNo,List<OrderGoodsModel> orderGoodsModel) {
        for (OrderGoodsModel goodsModel : orderGoodsModel) {
            StoreOrderGoods storeOrderGoods = new StoreOrderGoods();
            storeOrderGoods.setOrderNo(orderNo);
            storeOrderGoods.setGoodsId(goodsModel.getGoodsId());
            Goods goods = goodsService.getById(goodsModel.getGoodsId());
            storeOrderGoods.setGoodsName(goods.getName());
            storeOrderGoods.setGoodsNum(goodsModel.getGoodsNum());
            Map<String, Object> configParams = new HashMap<>();
            configParams.put("goodsId", goodsModel.getGoodsId());
            configParams.put("storeId", userUtils.getSessionUser().getStoreId());
            List<StoreGoodsConfig> storeGoodsList = storeGoodsConfigService.search(configParams);
            if (CollectionUtils.isNotEmpty(storeGoodsList)) {
                storeOrderGoods.setGoodsPrice(storeGoodsList.get(0).getShopPrice());
            } else {
                storeOrderGoods.setGoodsPrice(goods.getShopPrice());
            }
            storeOrderGoods.setCostPrice(goods.getCostPrice());
            storeOrderGoodsMapper.insertSelective(storeOrderGoods);
        }
    }

    public List<OrderGoodsDetailModel> searchDetail(Map<String, Object> params) {
        return storeOrderGoodsMapper.searchDetail(params);
    }

    public void deleteByIds(Integer id) {
        StoreOrderGoods storeOrderGoods = storeOrderGoodsMapper.selectByPrimaryKey(id);
        storeOrderGoodsMapper.updateByPrimaryKeySelective(storeOrderGoods);
    }
}

