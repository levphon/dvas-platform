package com.glsx.vasp.admin.modules.order.mapper;

import com.glsx.vasp.admin.modules.order.entity.StoreOrderGoods;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;

public interface StoreOrderGoodsMapper extends BaseMapper<StoreOrderGoods> {

    List<StoreOrderGoods> selectGoodsListByOrderNo(String orderNo);

}