package com.glsx.vasp.admin.modules.sys.mapper;

import com.glsx.vasp.admin.modules.sys.entity.SysOrganization;
import com.glsx.vasp.mapper.BaseMapper;

import java.util.List;

public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {

    SysOrganization selectByOrgId(Integer orgId);

    List<SysOrganization> selectChildrenOrgs(Integer orgId);

}