package com.glsx.vasp.exception;

import com.glsx.vasp.web.R;

public interface ExceptionCause<T extends Exception> {

    /**
     * 创建异常
     *
     * @return
     */
    T exception(Object... args);

    R result();
}
