package com.glsx.vasp.admin.modules.sys.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SysUserUpdatePwdModel implements Serializable {
    @NotNull(message = "旧密码不能为空")
    private String oldPassword;
    @NotNull(message = "新密码不能为空")
    private String newPassword;
    @NotNull(message = "账号ID不能为空")
    private Integer id;


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
