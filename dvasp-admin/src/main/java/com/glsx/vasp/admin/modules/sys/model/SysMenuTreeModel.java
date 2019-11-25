package com.glsx.vasp.admin.modules.sys.model;

import com.glsx.vasp.admin.modules.sys.entity.SysResource;
import com.glsx.vasp.model.TreeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树模型
 */
public class SysMenuTreeModel implements TreeModel<SysResource> {

    protected SysResource sysResource;

    protected List<TreeModel> children = new ArrayList<>();

    public SysMenuTreeModel(SysResource sysResource) {
        this.sysResource = sysResource;
    }

    public SysMenuTreeModel() {
    }

    @Override
    public Integer getId() {
        return sysResource.getMenuId();
    }

    @Override
    public String getLabel() {
        return sysResource.getMenuName();
    }

    @Override
    public boolean checked() {
        return false;
    }

    @Override
    public Integer getParentId() {
        return sysResource.getParentId();
    }

    @Override
    public SysResource getOrigin() {
        return sysResource;
    }

    @Override
    public List<TreeModel> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List childrens) {
        this.children = childrens;
    }

}
