package com.glsx.vasp.business.modules.user.mapper;

import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import com.glsx.vasp.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface StoreUserMapper extends BaseMapper<StoreUser> {

    /**
     * 通过电话获取用户信息
     */
    @DataPermAspect(showSql = true, tablePerix = "su")
    StoreUser selectUserByPhone(@Param(value = "phone") String phone);

    /**
     * 根据小程序openid获取用户
     *
     * @param miniOpenId
     * @return
     */
    @DataPermAspect(showSql = true, tablePerix = "su")
    StoreUser selectUserByMiniOpenId(String miniOpenId);

    /**
     * 更新用户miniopenid
     *
     * @param u
     * @return
     */
    @DataPermAspect(showSql = true, tablePerix = "")
    int updateMiniOpenIdByPrimaryKey(StoreUser u);

}
