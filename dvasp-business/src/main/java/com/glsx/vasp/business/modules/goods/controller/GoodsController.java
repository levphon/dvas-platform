package com.glsx.vasp.business.modules.goods.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.AbstractController;
import com.glsx.vasp.business.modules.category.service.CategoryService;
import com.glsx.vasp.business.modules.goods.entity.Goods;
import com.glsx.vasp.business.modules.goods.model.GoodsDetailModel;
import com.glsx.vasp.business.modules.goods.model.GoodsModel;
import com.glsx.vasp.business.modules.goods.service.GoodsService;
import com.glsx.vasp.business.modules.goodsPicture.entity.GoodsPicture;
import com.glsx.vasp.business.modules.goodsPicture.service.GoodsPictureService;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.business.modules.store.service.StoreService;
import com.glsx.vasp.business.modules.storeGoods.entity.StoreGoodsConfig;
import com.glsx.vasp.business.modules.storeGoods.service.StoreGoodsConfigService;
import com.glsx.vasp.web.Pagination;
import com.glsx.vasp.web.R;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品表
 *
 * @author liumh
 * @date 2019-04-01 17:52:54
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends AbstractController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StoreGoodsConfigService storeGoodsConfigService;

    @Autowired
    GoodsPictureService goodsPictureService;

    @Autowired
    StoreService storeService;

    /**
     * 同类型商品列表
     */
    @AuthCheck
    @RequestMapping("/list")
    public R list(String categoryCode) {
        Map<String, Object> params = new HashMap<>();
        //查询门店审核状态
        Store store = storeService.selectByPrimaryKey(getStoreId());
        if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
            Map<String, Object> storeParams = new HashMap<>();
            storeParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
            storeParams.put("id", getStoreId());
            List<Store> list = storeService.search(storeParams);
            //是否有sp
            if (CollectionUtils.isNotEmpty(list) && list.get(0).getOrgId() != null)
                params.put("orgId", list.get(0).getOrgId());
        } else {
            //没有设置广联自营
            params.put("orgId", 1);//广联
            params.put("glSelfSupport", 1);//自营
        }
        params.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        params.put("categoryCode", categoryCode);
        List<Goods> list = goodsService.search(params);
        return R.ok().put("result", list);
    }

    /**
     * 不同类型商品列表
     */
    @AuthCheck
    @RequestMapping("/diffList")
    public R diffList(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        params.put("code", code);
        Store store = storeService.selectByPrimaryKey(getStoreId());
        if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
            Map<String, Object> storeParams = new HashMap<>();
            storeParams.put("id", getStoreId());
            List<Store> list = storeService.search(storeParams);
            //是否有sp
            if (CollectionUtils.isNotEmpty(list) && list.get(0).getOrgId() != null)
                params.put("orgId", list.get(0).getOrgId());
        } else {
            //没有设置广联自营
            params.put("orgId", 1);//广联
            params.put("glSelfSupport", 1);//自营
        }
        List<Goods> list = goodsService.search(params);
        List<GoodsModel> detailModels = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, List<Goods>> map = new HashMap<>();
            for (Goods goods : list) {
                if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
                    Map<String, Object> configParams = new HashMap<>();
                    configParams.put("goodsId", goods.getId());
                    configParams.put("storeId", getStoreId());
                    List<StoreGoodsConfig> storeGoodsList = storeGoodsConfigService.search(configParams);
                    if (CollectionUtils.isNotEmpty(storeGoodsList))
                        goods.setShopPrice(storeGoodsList.get(0).getShopPrice());
                }else{
                    goods.setShopPrice(null);
                }
                if (map.containsKey(goods.getCategoryCode())) {
                    //map中存在此ParentCode，将数据存放当前key的map中
                    map.get(goods.getCategoryCode()).add(goods);
                } else {
                    //map中不存在，新建key，用来存放数据
                    List<Goods> tmpList = new ArrayList<>();
                    tmpList.add(goods);
                    map.put(goods.getCategoryCode(), tmpList);
                }
            }

            for (String key : map.keySet()) {
                GoodsModel detailModel = new GoodsModel();
                detailModel.setCategoryCode(key);
                detailModel.setGoodsDetail(map.get(key));
                detailModels.add(detailModel);
            }
        }
        return R.ok().put("result", detailModels);
    }


    /**
     * 商品详情信息
     */
    @RequestMapping("/info")
    @AuthCheck
    public R info(Integer id) {
        Map<String, Object> goodsParams = new HashMap<>();
        goodsParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        goodsParams.put("id", id);
        Goods goods = goodsService.search(goodsParams).get(0);
        Store store = storeService.selectByPrimaryKey(getStoreId());
        if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
            Map<String, Object> configParams = new HashMap<>();
            configParams.put("goodsId", goods.getId());
            configParams.put("storeId", getStoreId());
            List<StoreGoodsConfig> list = storeGoodsConfigService.search(configParams);
            if (CollectionUtils.isNotEmpty(list)) goods.setShopPrice(list.get(0).getShopPrice());
        }

        Map<String, Object> goodsPicParams = new HashMap<>();
        //goodsPicParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        goodsPicParams.put("goodsId", goods.getId());
        GoodsDetailModel goodsDetailModel = new GoodsDetailModel();
        BeanUtils.copyProperties(goods, goodsDetailModel);
        //针对多张图片
        List<GoodsPicture> pictures = goodsPictureService.search(goodsPicParams);
        List<String> picUrls = new ArrayList<>();
        if (pictures.size() > 1) {
            for (GoodsPicture picture : pictures) {
                picUrls.add(picture.getPicUrl());
            }
        } else {
            picUrls.add(goods.getPicUrl());
        }
        goodsDetailModel.setPicUrl(picUrls);
        Map<String, Object> result = new HashMap<>();
        result.put("goods", goodsDetailModel);
        result.put("authCheck", 0 );//是否登录0未登录，1已登录
        if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
            result.put("authCheck",  1);//是否登录0未登录，1已登录
        }
        return R.ok().put("result", result);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Goods dGoods) {
        //dGoodsService.save(dGoods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Goods dGoods) {
        //ValidatorUtils.validateEntity(dGoods);
        //dGoodsService.updateById(dGoods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        //dGoodsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 模糊查询商品
     */
    @AuthCheck
    @RequestMapping("/getListByName")
    public R getListByName(Pagination pagination, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", getUserId());
        params.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
        params.put("name", name);
        Store store = storeService.selectByPrimaryKey(getStoreId());
        if (store != null && store.getDelFlag() == Constants.NOT_DEL && store.getEnableStatus() == Constants.ENABLE_STATUS_EFFECT) {
            Map<String, Object> storeParams = new HashMap<>();
            storeParams.put("enableStatus", Constants.ENABLE_STATUS_EFFECT);
            storeParams.put("id", getStoreId());
            List<Store> list = storeService.search(storeParams);
            //是否有sp
            if (CollectionUtils.isNotEmpty(list) && list.get(0).getOrgId() != null)
                params.put("orgId", list.get(0).getOrgId());
        } else {
            //没有设置广联自营
            params.put("orgId", 1);//广联
            params.put("glSelfSupport", 1);//自营
        }
        PageHelper.startPage(pagination.getCurrentPage(), pagination.getPageSize());
        List<Goods> list = goodsService.search(params);
        long total = ((Page<Goods>) list).getTotal();
        return R.ok().putPageData(list, total);
    }
}
