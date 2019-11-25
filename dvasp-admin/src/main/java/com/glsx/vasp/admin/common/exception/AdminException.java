package com.glsx.vasp.admin.common.exception;

import com.glsx.vasp.exception.BusinessException;
import com.glsx.vasp.exception.ExceptionCause;

public class AdminException extends BusinessException {

    protected AdminException(BusinessException parent) {
        super(parent);
    }

    public static AdminException create(ExceptionCause exceptionCause, Object... args) {
        BusinessException businessException = BusinessException.create(exceptionCause, args);
        return new AdminException(businessException);
    }

}
