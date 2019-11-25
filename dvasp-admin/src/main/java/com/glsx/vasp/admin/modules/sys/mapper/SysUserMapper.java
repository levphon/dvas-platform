package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.admin.modules.sys.entity.SysUser;
import com.glsx.vasp.annotation.DataPermAspect;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 查询系统用户列表
     *
     * @param params
     * @return
     */
    @DataPermAspect(showSql = true, tablePerix = "u")
    List<SysUser> search(Map<String, Object> params);

    /**
     * 根据账号获取用户
     *
     * @param account
     * @return
     */
    SysUser selectByAccount(String account);

}