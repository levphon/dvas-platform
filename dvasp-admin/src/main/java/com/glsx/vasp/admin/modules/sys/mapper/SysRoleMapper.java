package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.mapper.BaseMapper;
import com.glsx.vasp.admin.modules.sys.entity.SysRole;
import com.glsx.vasp.admin.modules.sys.entity.SysUser;
import com.glsx.vasp.admin.modules.sys.model.SearchSysRoleModel;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据角色名称查询角色列表
     *
     * @param roleName
     * @return
     */
    List<SysRole> selectRoleByName(@Param("roleName") String roleName);

    /**
     * 角色列表模糊查询
     *
     * @param roleName
     * @return
     */
    List<SysRole> search(SearchSysRoleModel roleSearchModel);

    /**
     * 查询用户可见角色列表
     *
     * @param userId
     * @return
     */
    List<Role> selectRoleList(SysUser sysUser);

}