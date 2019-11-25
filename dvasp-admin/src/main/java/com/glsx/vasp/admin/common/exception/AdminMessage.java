package com.glsx.vasp.admin.common.exception;

import com.glsx.vasp.exception.ExceptionCause;
import com.glsx.vasp.web.R;

public enum AdminMessage implements ExceptionCause<AdminException> {

    CHECK_CODE_ERROR(6001, "验证码错误"),
    ACCOUNT_NUll(6002, "帐户或密码错误"),
    ACCOUNT_VERIFYING(6005, "账号信息待审核"),
    ACCOUNT_VERIFYNOT(6006, "账号审核不通过");

    private R resultEntity = new R();

    private AdminMessage(int returnCode, String message) {
        resultEntity.setCode(returnCode);
        resultEntity.setMessage(message);
    }

    @Override
    public AdminException exception(Object... args) {
        return AdminException.create(this, args);
    }

    @Override
    public R result() {
        return this.resultEntity;
    }

}
