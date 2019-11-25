package com.glsx.vasp.business.modules.store.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "d_store")
@ApiModel("门店用户")
public class Store extends BaseEntity {

    /**
     * 机构id
     */
    @Column(name = "org_id", length = 20)
    private Integer orgId;

    /**
     * 门店名称
     */
    @Column(name = "name", length = 20)
    private String name;

    /**
     * 联系人
     */
    @Column(name = "contactor", length = 20)
    private String contactor;

    /**
     * 手机号
     */
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * 手机号1
     */
    /*@Column(name = "phone1", length = 11)
    private String phone1;

    *//**
     * 手机号2
     *//*
    @Column(name = "phone2", length = 11)
    private String phone2;*/

    /**
     * 省份code
     */
    @Column(name = "province", length = 20)
    private String province;

    /**
     * 地市code
     */
    @Column(name = "city", length = 20)
    private String city;

    /**
     * 辖区
     */
    @Column(name = "district", length = 10)
    private String district;

    /**
     * 地址
     */
    @Column(name = "address", length = 30)
    private String address;

    /**
     * 备注
     */
    @Column(name = "remark", length = 50)
    private String remark;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}