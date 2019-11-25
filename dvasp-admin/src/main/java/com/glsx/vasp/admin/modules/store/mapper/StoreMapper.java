package com.glsx.vasp.admin.modules.store.mapper;

import com.glsx.vasp.admin.modules.store.entity.Store;
import com.glsx.vasp.admin.modules.store.model.StoreExportModel;
import com.glsx.vasp.admin.modules.store.model.StoreRegionVO;
import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StoreMapper extends BaseMapper<Store> {

    /**
     * 查询门店列表
     *
     * @param params
     * @return
     */
    @DataPermAspect(showSql = true, tablePerix = "ds")
    List<Store> search(Map<String, Object> params);

    @DataPermAspect(showSql = true, tablePerix = "ds")
    List<StoreExportModel> export(Map<String, Object> params);

    @DataPermAspect(showSql = true, tablePerix = "ds")
    Store selectByName(@Param("storeName") String storeName);

    @DataPermAspect(showSql = true, tablePerix = "ds")
    List<StoreRegionVO> selectListByName(Map<String, Object> params);

    int changeStatusById(@Param("storeId") Integer storeId, @Param("enableStatus") Integer status);

    int logicDeleteBatch(Integer[] storeIds);

}