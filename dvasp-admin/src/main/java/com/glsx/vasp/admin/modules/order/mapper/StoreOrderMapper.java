package com.glsx.vasp.admin.modules.order.mapper;

import com.glsx.vasp.admin.modules.order.entity.StoreOrder;
import com.glsx.vasp.admin.modules.order.model.StoreOrderExportModel;
import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface StoreOrderMapper extends BaseMapper<StoreOrder> {

    @DataPermAspect(showSql = true, tablePerix = "ds")
    List<StoreOrder> search(Map<String, Object> params);

    @DataPermAspect(showSql = true, tablePerix = "ds")
    List<StoreOrderExportModel> export(Map<String, Object> params);

}