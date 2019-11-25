package com.glsx.vasp.business.modules.shopcar.service;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.utils.UserUtils;
import com.glsx.vasp.business.modules.coupon.service.CouponRecipientsService;
import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.business.modules.goods.service.GoodsService;
import com.glsx.vasp.business.modules.shopcar.entity.Shopcar;
import com.glsx.vasp.business.modules.shopcar.entity.ShopcarDetail;
import com.glsx.vasp.business.modules.shopcar.mapper.ShopcarMapper;
import com.glsx.vasp.business.modules.shopcar.model.ShopcarModel;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.business.modules.store.service.StoreService;
import com.glsx.vasp.business.modules.storeGoods.entity.StoreGoodsConfig;
import com.glsx.vasp.business.modules.storeGoods.service.StoreGoodsConfigService;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ShopcarService {

    private static final Logger logger = LoggerFactory.getLogger(ShopcarService.class);

    @Autowired
    ShopcarMapper shopcarMapper;

    @Autowired
    CouponRecipientsService couponRecipientsService;

    @Autowired
    UserUtils userUtils;

    @Autowired
    GoodsService goodsService;

    @Autowired
    StoreGoodsConfigService storeGoodsConfigService;

    @Autowired
    StoreService storeService;


    public void save(ShopcarModel shopcarModel) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userUtils.getSessionUser().getId());
        params.put("goodsId", shopcarModel.getGoodsId());
        params.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);//正常
        List<Shopcar> shopcarList = shopcarMapper.search(params);
        if (CollectionUtils.isNotEmpty(shopcarList)) {
            //存在相同商品
            Shopcar shopcar = shopcarList.get(0);
            logger.info("新增商品数量:" + shopcarModel.getGoodsNum() + "购物车相同商品数量:" + shopcar.getGoodsNum());
            int goodsNum = shopcarModel.getGoodsNum() + shopcar.getGoodsNum();
            shopcar.setGoodsNum(goodsNum > 10 ? 10 : goodsNum);
            shopcarMapper.updateByPrimaryKeySelective(shopcar);
        } else {
            //没有相同商品
            logger.info("新增商品数量:" + shopcarModel.getGoodsNum());
            Shopcar shopcar = new Shopcar();
            BeanUtils.copyProperties(shopcarModel, shopcar);
            int goodsNum = shopcar.getGoodsNum();
            shopcar.setGoodsNum(goodsNum > 10 ? 10 : goodsNum);
            shopcar.setEnableStatus(Constants.ENABLE_STATUS_EFFECT);
            shopcar.setUserId(userUtils.getSessionUser().getId());
            shopcarMapper.insertSelective(shopcar);
        }
    }

    public void saveList(List<ShopcarModel> list) {
        if (!CollectionUtils.isEmpty(list)) {
            for (ShopcarModel shopcarModel : list) {
                StoreUser sessionUser = userUtils.getSessionUser();
                HashMap<String, Object> params = new HashMap<>();
                params.put("userId", sessionUser.getId());
                params.put("goodsId", shopcarModel.getGoodsId());
                params.put("delFlag", 0);//未删除
                params.put("enableStatus", 1);//正常
                List<Shopcar> shopcarList = shopcarMapper.search(params);
                if (CollectionUtils.isNotEmpty(shopcarList)) {
                    //存在相同商品
                    Shopcar shopcar = shopcarList.get(0);
                    logger.info("新增商品数量:" + shopcarModel.getGoodsNum() + "购物车相同商品数量:" + shopcar.getGoodsNum());
                    int goodsNum = shopcarModel.getGoodsNum() + shopcar.getGoodsNum();
                    shopcar.setGoodsNum(goodsNum > 10 ? 10 : goodsNum);
                    shopcarMapper.updateByPrimaryKeySelective(shopcar);
                } else {
                    //没有相同商品
                    logger.info("新增商品数量:" + shopcarModel.getGoodsNum());
                    Shopcar target = new Shopcar();
                    BeanUtils.copyProperties(shopcarModel, target);
                    int goodsNum = target.getGoodsNum();
                    target.setGoodsNum(goodsNum > 10 ? 10 : goodsNum);
                    target.setUserId(sessionUser.getId());
                    target.setEnableStatus(1);
                    shopcarMapper.insert(target);
                }
            }
        }
    }

    public List<ShopcarDetail>  searchShopcarDetail(Map<String, Object> params) {
        StoreUser sessionUser = userUtils.getSessionUser();
        List<ShopcarDetail> detailList = shopcarMapper.searchShopcarDetail(params);
        for (ShopcarDetail shopcarDetail : detailList) {
            Store store = storeService.selectByPrimaryKey(sessionUser.getStoreId());
            if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
                HashMap<String, Object> goodsParams = new HashMap<>();
                goodsParams.put("userId", sessionUser.getId());
                goodsParams.put("baseGoodsId", shopcarDetail.getGoodsId());
                goodsParams.put("orgId", store.getOrgId());
                goodsParams.put("delFlag", Constants.NOT_DEL);//未删除
                goodsParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);//正常
                List<Goods> goodsList = goodsService.search(goodsParams);
                Shopcar shopcar = new Shopcar();
                Map<String, Object> configParams = new HashMap<>();
                configParams.put("goodsId", shopcarDetail.getId());
                configParams.put("storeId", userUtils.getSessionUser().getStoreId());
                //门店有相同的商品转为门店goodsId
                if (CollectionUtils.isNotEmpty(goodsList)) {
                    Goods goods = goodsList.get(0);
                    shopcar.setId(shopcarDetail.getId());
                    shopcar.setGoodsId(goods.getId());
                    shopcarMapper.updateByPrimaryKeySelective(shopcar);
                    configParams.put("goodsId", goods.getId());
                    shopcarDetail.setGoodsId(goods.getId());
                }

                //门店到店价格
                List<StoreGoodsConfig> storeGoodsList = storeGoodsConfigService.search(configParams);
                if (CollectionUtils.isNotEmpty(storeGoodsList))
                    shopcarDetail.setShopPrice(storeGoodsList.get(0).getShopPrice());
            }
        }
        return detailList;
    }

    public List<Shopcar> search(Map<String, Object> params) {
        return shopcarMapper.search(params);
    }

    public void updateById(Shopcar shopcar) {
        int goodsNum = shopcar.getGoodsNum();
        shopcar.setGoodsNum(goodsNum > 10 ? 10 : goodsNum);
        shopcarMapper.updateByPrimaryKeySelective(shopcar);
    }

    public Shopcar selectByPrimaryKey(Integer id) {
        return shopcarMapper.selectByPrimaryKey(id);
    }

    public int delete(Shopcar shopcar) {
        shopcar.setDelFlag(Constants.IS_DEL);
        return shopcarMapper.updateByPrimaryKeySelective(shopcar);
    }
}
