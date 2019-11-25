package com.glsx.vasp.admin.modules.sys.service;

import com.glsx.vasp.admin.modules.sys.entity.SysOrganization;
import com.glsx.vasp.admin.modules.sys.mapper.SysOrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 3/19/2019 15:06
 * @since 1.0.0
 */
@Service
public class SysOrganizationService {

    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;

    public SysOrganization selectByOrgId(Integer orgId) {
        return sysOrganizationMapper.selectByOrgId(orgId);
    }

    public List<SysOrganization> list() {
        return sysOrganizationMapper.selectAll();
    }

    /**
     * 获取所有子级机构,包含自身
     *
     * @param orgId
     */
    public List<SysOrganization> selectChildrenOrgsSelf(Integer orgId) {
        List<SysOrganization> sysOrganizations = sysOrganizationMapper.selectAll();
        return sysOrganizations.stream().filter(org -> (org.getOrgId().equals(orgId)) || org.getParentId().equals(orgId)).collect(Collectors.toList());
    }

    /**
     * 获取所有子级机构,不包含自身
     *
     * @param orgId
     */
    private List<SysOrganization> selectChildrenOrgs(Integer orgId) {
        List<SysOrganization> sysOrganizations = sysOrganizationMapper.selectAll();
        return sysOrganizations.stream().filter(org -> (!org.getOrgId().equals(orgId)) && org.getParentId().equals(orgId)).collect(Collectors.toList());
    }

}
