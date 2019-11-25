package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.admin.modules.sys.entity.SysUserRole;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    Set<String> selectRoleIdListByUserId(Integer userId);

}