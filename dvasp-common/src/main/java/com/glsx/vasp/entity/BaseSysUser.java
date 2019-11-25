package com.glsx.vasp.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

/**
 * 〈一句话功能简述〉<br>
 * 所有系统的用户类都继承BaseSysUser
 *
 * @author payu
 * @create 3/20/2019 10:19
 * @since 1.0.0
 */
public abstract class BaseSysUser extends BaseEntity {

    /**
     * 帐号
     */
    @Column(name = "account", length = 64)
    @ApiModelProperty("帐号")
    private String account;

    /**
     * 密码
     */
    @Column(name = "password", length = 128)
    @ApiModelProperty("密码")
    private String password;

    /**
     * 用户名
     */
    @Column(name = "username", length = 64)
    @ApiModelProperty("用户名")
    private String username;

    /**
     * （真实）姓名
     */
    @Column(name = "realname", length = 64)
    @ApiModelProperty("真实姓名")
    private String realname;

    /**
     * 手机号
     */
    @Column(name = "phone", length = 20)
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 头像链接
     */
    @Column(name = "avatar", length = 150)
    @ApiModelProperty("头像链接")
    private String avatar;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
