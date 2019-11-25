package com.glsx.vasp.admin.common.config.shiro;

import com.glsx.vasp.admin.common.exception.AdminException;
import com.glsx.vasp.admin.common.exception.AdminMessage;
import com.glsx.vasp.web.R;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShiroExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public R unknownAccount(AccountException e) {
        if (e instanceof UnknownAccountException) {
            return AdminMessage.ACCOUNT_NUll.result();
        } else if (e instanceof DisabledAccountException) {
            return AdminException.create("帐号被禁用").getResultEntity();
        }
        return AdminException.create("帐号异常").getResultEntity();
    }

}
