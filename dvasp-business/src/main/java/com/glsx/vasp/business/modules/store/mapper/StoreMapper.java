package com.glsx.vasp.business.modules.store.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.store.entity.Store;
import com.glsx.vasp.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface StoreMapper extends BaseMapper<Store> {

    /**
     * 查询门店列表
     *
     * @param params
     * @return
     */
    @DataPermAspect(showSql = true, tablePerix = "ds")
    List<Store> search(Map<String, Object> params);

}