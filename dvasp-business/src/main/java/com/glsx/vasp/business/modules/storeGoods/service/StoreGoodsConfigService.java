package com.glsx.vasp.business.modules.storeGoods.service;

import com.glsx.vasp.business.modules.storeGoods.entity.StoreGoodsConfig;
import com.glsx.vasp.business.modules.storeGoods.mapper.StoreGoodsConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * (由平台)配置到门店的商品到店价
 *
 * @author liumh
 * @date 2019-04-15 14:56:01
 */
@Service
public class StoreGoodsConfigService  {

    @Autowired
    StoreGoodsConfigMapper storeGoodsConfigMapper;

    public List<StoreGoodsConfig> search(Map<String, Object> params) {
        return storeGoodsConfigMapper.search(params);
    }
}

