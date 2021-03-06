/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.glsx.vasp.validator;

import com.glsx.vasp.exception.ValidateException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new ValidateException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ValidateException(message);
        }
    }
}
