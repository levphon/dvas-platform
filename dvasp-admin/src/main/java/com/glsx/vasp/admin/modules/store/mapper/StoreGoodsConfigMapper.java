package com.glsx.vasp.admin.modules.store.mapper;

import com.glsx.vasp.admin.modules.store.entity.StoreGoodsConfig;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface StoreGoodsConfigMapper extends BaseMapper<StoreGoodsConfig> {

    StoreGoodsConfig selectByStoreIdAndGoodsId(@Param("storeId") Integer storeId, @Param("goodsId") Integer goodsId);

}