package com.glsx.vasp.business.modules.user.entity;

import com.glsx.vasp.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "d_store_user")
@ApiModel("门店用户")
public class StoreUser extends BaseEntity {

    private static final long serialVersionUID = 3065150715942860165L;

    @ApiModelProperty("账号id")
    @Column(name = "id", length = 20)
    private Integer id;

    @ApiModelProperty("手机号")
    @Column(name = "phone", length = 11)
    private String phone;

    @ApiModelProperty("微信公众号openid")
    @Column(name = "wx_openid", length = 64)
    private String wxOpenId;

    @Column(name = "store_id", length = 20)
    private Integer storeId;

    @ApiModelProperty("关联微信小程序openid")
    @Column(name = "mini_openid", length =64)
    private String miniOpenId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }


    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId == null ? null : wxOpenId.trim();
    }

    public String getMiniOpenId() {
        return miniOpenId;
    }

    public void setMiniOpenId(String miniOpenId) {
        this.miniOpenId = miniOpenId == null ? null : miniOpenId.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "StoreUser{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", storeId=" + storeId +
                ", miniOpenId='" + miniOpenId + '\'' +
                '}';
    }
}