package com.glsx.vasp.admin.modules.store.mapper;

import com.glsx.vasp.admin.modules.store.entity.StoreUser;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreUserMapper extends BaseMapper<StoreUser> {

    List<StoreUser> selectByStoreId(Integer storeId);

    StoreUser selectByPhone(@Param("phone") String phone);

    List<StoreUser> selectByPhones(String[] phones);

    int changeStatusByStoreId(Integer storeId, @Param("enableStatus") Integer status);

    int logicDelete(Integer id);

    int logicDeleteByStoreId(@Param("storeId") Integer storeId);

}