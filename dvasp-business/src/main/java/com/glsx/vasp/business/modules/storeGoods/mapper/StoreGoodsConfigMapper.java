package com.glsx.vasp.business.modules.storeGoods.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.storeGoods.entity.StoreGoodsConfig;
import com.glsx.vasp.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * (由平台)配置到门店的商品到店价
 * 
 * @author liumh
 * @date 2019-04-15 14:56:01
 */
@Component
public interface StoreGoodsConfigMapper extends BaseMapper<StoreGoodsConfig> {

    @DataPermAspect(showSql = true, tablePerix = "dsgc")
    List<StoreGoodsConfig> search(Map<String,Object> params);
}
