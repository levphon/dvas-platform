package com.glsx.vasp.admin.modules.sys.model;

import com.glsx.vasp.admin.modules.sys.entity.SysOrganization;
import com.glsx.vasp.model.TreeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织机构树模型
 */
public class SysOrganizationTreeModel implements TreeModel<SysOrganization> {

    protected SysOrganization sysOrganization;

    protected List<TreeModel> children = new ArrayList<>();

    public SysOrganizationTreeModel(SysOrganization sysOrganization) {
        this.sysOrganization = sysOrganization;
    }

    public SysOrganizationTreeModel() {
    }

    @Override
    public Integer getId() {
        return sysOrganization.getOrgId();
    }

    @Override
    public Integer getParentId() {
        return sysOrganization.getParentId();
    }

    @Override
    public String getLabel() {
        return sysOrganization.getName();
    }

    @Override
    public boolean checked() {
        return false;
    }

    @Override
    public SysOrganization getOrigin() {
        return sysOrganization;
    }

    @Override
    public List<TreeModel> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List childrens) {
        this.children = childrens;
    }

    public Integer getOrgLevel() {
        return sysOrganization.getOrgLevel();
    }

}
