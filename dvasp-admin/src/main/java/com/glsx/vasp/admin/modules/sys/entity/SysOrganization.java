package com.glsx.vasp.admin.modules.sys.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 组织机构
 */
@Entity
@Table(name = "sys_organization")
@ApiModel("组织机构")
public class SysOrganization extends BaseEntity {

    @Column(name = "org_id", length = 8)
    @ApiModelProperty("机构ID")
    protected Integer orgId;

    /**
     * 机构名称
     */
    @Column(name = "name", length = 64)
    @ApiModelProperty("机构名称")
    protected String name;

    @Column(name = "parent_id", length = 8)
    @ApiModelProperty("父级ID")
    protected Integer parentId;

    // 联系人
    @Column(name = "contactor", length = 64)
    @ApiModelProperty("联系人")
    protected String contactor;

    // 手机号
    @Column(name = "contactor_phone", length = 20)
    @ApiModelProperty("联系人电话")
    protected String contactorPhone;

    // 使有$区分省,市,和详细地址
    @Column(name = "province", length = 30)
    @ApiModelProperty("省份")
    protected String province;
    @Column(name = "city", length = 30)
    @ApiModelProperty("地市")
    protected String city;
    @Column(name = "address", length = 256)
    @ApiModelProperty("详细地址")
    protected String address;

    @Column(name = "org_type", length = 10)
    @ApiModelProperty("机构类型: 1、渠道商 (默认) 2、4s店  3、工厂 4、精品商  0、其他")
    protected Integer orgType;

    @Column(name = "org_level", length = 10)
    @ApiModelProperty("机构级别：")
    protected Integer orgLevel;

    // 车队描述
    @Column(name = "remark", length = 200)
    @ApiModelProperty("车队描述")
    protected String remark;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    // setGet contactor
    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getContactor() {
        return this.contactor;
    }

    public String getContactorPhone() {
        return contactorPhone;
    }

    public void setContactorPhone(String contactorPhone) {
        this.contactorPhone = contactorPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getOrgLevel() {
        return this.orgLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
