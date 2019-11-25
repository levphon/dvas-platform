package com.glsx.vasp.business.modules;

import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import com.glsx.vasp.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author qurong
 * @Title BaseController.java
 * @Package com.qurong.controller
 * @Description
 * @date 2018年10月24日 下午2:24:00
 */
@Controller
public class AbstractController extends BaseController {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpSession session;

    /**
     * 从session中获取当前用户
     *
     * @return
     */
    public StoreUser getSessionUser() {
        StoreUser user = (StoreUser) session.getAttribute(Constants.SESSION_LOGIN_USER);
        if (user == null || StringUtils.isBlank(user.getPhone())) {
            return null;
        }
        return user;
    }

    public String getUserName() {
        StoreUser user = getSessionUser();
        if (user != null) {
            return user.getPhone();
        }
        return null;
    }

    public Integer getUserId() {
        StoreUser user = getSessionUser();
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    public Integer getStoreId() {
        StoreUser user = getSessionUser();
        if (user != null) {
            return user.getStoreId();
        }
        return null;
    }
}
