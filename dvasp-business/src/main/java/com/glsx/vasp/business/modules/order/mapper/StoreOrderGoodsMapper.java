package com.glsx.vasp.business.modules.order.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.order.entity.StoreOrder;
import com.glsx.vasp.business.modules.order.entity.StoreOrderGoods;
import com.glsx.vasp.business.modules.order.model.OrderGoodsDetailModel;
import com.glsx.vasp.business.modules.order.model.StoreOrderGoodsModel;
import com.glsx.vasp.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 订单商品关系表
 * 
 * @author liumh
 * @date 2019-04-09 15:03:07
 */
@Component
public interface StoreOrderGoodsMapper extends BaseMapper<StoreOrderGoods> {

    @DataPermAspect(showSql = true, tablePerix = "")
    List<OrderGoodsDetailModel> searchDetail(Map<String,Object> params);
}
