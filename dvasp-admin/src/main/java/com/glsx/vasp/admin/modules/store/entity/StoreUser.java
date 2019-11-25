package com.glsx.vasp.admin.modules.store.entity;

import com.glsx.vasp.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "d_store_user")
public class StoreUser extends BaseEntity {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信公众号openid
     */
    @Column(name = "wx_openid")
    private String wxOpenid;

    /**
     * 关联微信小程序openid
     */
    @Column(name = "mini_openid")
    private String miniOpenid;

    /**
     * 用户所属门店
     */
    @Column(name = "store_id")
    private Integer storeId;

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
     * 获取微信公众号openid
     *
     * @return wx_openid - 微信公众号openid
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     * 设置微信公众号openid
     *
     * @param wxOpenid 微信公众号openid
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    /**
     * 获取关联微信小程序openid
     *
     * @return mini_openid - 关联微信小程序openid
     */
    public String getMiniOpenid() {
        return miniOpenid;
    }

    /**
     * 设置关联微信小程序openid
     *
     * @param miniOpenid 关联微信小程序openid
     */
    public void setMiniOpenid(String miniOpenid) {
        this.miniOpenid = miniOpenid == null ? null : miniOpenid.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}