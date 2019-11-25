package com.glsx.vasp.admin.modules.sys.entity;

import com.glsx.vasp.entity.BaseSysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * 管理员
 */
@Entity
@Table(name = "sys_user")
@ApiModel("系统用户,管理员")
public class SysUser extends BaseSysUser {

    /**
     * 平台账号id
     */
    @ApiModelProperty("平台账号id")
    @Column(name = "account_id", length = 30)
    private Integer accountId;

    /**
     * 所属机构
     */
    @ApiModelProperty("所属机构")
    @Column(name = "org_id", length = 30)
    private Integer orgId;

    /**
     * 备注
     */
    @Column(name = "remark", length = 300)
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 所属角色
     */
    @ApiModelProperty("所属角色")
    private Set<String> roleIdList;

    /**
     * 解决 shiro bug ,不能取到父级的 id 信息; 无法序列化
     *
     * @return
     */
    @Override
    public Integer getId() {
        return super.getId();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(Set<String> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
