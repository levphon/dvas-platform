package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.mapper.BaseMapper;
import com.glsx.vasp.admin.modules.sys.entity.SysRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {
    /**
     * 修改角色权限
     *
     * @param roleId
     * @param sysResources
     */
    void updateRolePerm(@Param("roleId") Integer roleId, @Param("sysResources") List<String> sysResources);

    /**
     * 删除关联
     *
     * @param roleId
     */
    void deleteRoleResource(@Param("roleId") Integer roleId);
}