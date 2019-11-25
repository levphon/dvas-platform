package com.glsx.vasp.admin.modules.sys.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 管理员
 */
@Entity
@Table(name = "sys_user_role")
@ApiModel("系统用户,管理员")
public class SysUserRole extends BaseEntity {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
