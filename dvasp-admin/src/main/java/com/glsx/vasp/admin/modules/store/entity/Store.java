package com.glsx.vasp.admin.modules.store.entity;

import com.glsx.vasp.entity.BaseEntity;
import com.glsx.vasp.validator.group.AddGroup;
import com.glsx.vasp.validator.group.UpdateGroup;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Table(name = "d_store")
public class Store extends BaseEntity {

    /**
     * 机构id
     */
    @Column(name = "org_id", length = 15)
    private Integer orgId;

    /**
     * 门店名称
     */
    @NotBlank(message = "门店名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 联系人
     */
    @NotBlank(message = "门店联系人不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String contactor;

    /**
     * 手机号
     */
    @NotBlank(message = "门店联系人号码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String phone;

    /**
     * 省份code
     */
    @NotBlank(message = "门店所在省份不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String province;

    /**
     * 地市code
     */
    @NotBlank(message = "门店所在地市不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String city;

    /**
     * 辖区
     */
    private String district;

    /**
     * 地址
     */
    private String address;
    @Transient
    private String fullAddress;


    /**
     * 备注
     */
    private String remark;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取门店名称
     *
     * @return name - 门店名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置门店名称
     *
     * @param name 门店名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取联系人
     *
     * @return contactor - 联系人
     */
    public String getContactor() {
        return contactor;
    }

    /**
     * 设置联系人
     *
     * @param contactor 联系人
     */
    public void setContactor(String contactor) {
        this.contactor = contactor == null ? null : contactor.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取省份code
     *
     * @return province - 省份code
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份code
     *
     * @param province 省份code
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取地市code
     *
     * @return city - 地市code
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置地市code
     *
     * @param city 地市code
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取辖区
     *
     * @return district - 辖区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置辖区
     *
     * @param district 辖区
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}