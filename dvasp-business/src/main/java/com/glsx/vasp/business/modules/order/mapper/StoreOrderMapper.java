package com.glsx.vasp.business.modules.order.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.order.entity.StoreOrder;
import com.glsx.vasp.business.modules.order.model.StoreOrderModel;
import com.glsx.vasp.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 门店订单表
 * 
 * @author liumh
 * @date 2019-04-09 14:21:28
 */
@Component
public interface StoreOrderMapper extends BaseMapper<StoreOrder> {

    @DataPermAspect(showSql = true, tablePerix = "")
    List<StoreOrderModel> searchStoreOrderMode(Map<String,Object> params);}
