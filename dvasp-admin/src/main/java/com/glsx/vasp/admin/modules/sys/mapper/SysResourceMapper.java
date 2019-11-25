package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.mapper.BaseMapper;
import com.glsx.vasp.admin.modules.sys.entity.SysResource;

import java.util.List;

public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResource> selectResourcesByRole(Integer roleId);

    List<SysResource> selectResourcesByUserId(Integer userId);

}