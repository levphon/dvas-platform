package com.glsx.vasp.admin.modules.sys.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色资源关联表
 */
@Entity
@Table(name="sys_role_menu")
public class SysRoleResource extends BaseEntity{

	private Integer roleId;

	private Integer menuId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}
