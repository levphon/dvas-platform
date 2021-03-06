package com.glsx.vasp.business.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.glsx.vasp.business.common.annotation.AuthCheck;
import com.glsx.vasp.business.common.constant.Constants;
import com.glsx.vasp.business.common.exception.ResultCodeEnum;
import com.glsx.vasp.business.common.utils.UserUtils;
import com.glsx.vasp.business.modules.user.entity.StoreUser;
import com.glsx.vasp.business.modules.user.service.StoreUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qurong
 * @Title AuthInterceptor.java
 * @Package com.qurong.credit.sp.interceptor
 * @Description 登录校验拦截器
 * @date 2018年7月18日 下午4:20:58
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    StoreUserService storeUserService;
    @Autowired
    UserUtils userUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //检查方法上是否有校验登陆标注
        AuthCheck authCheck = handlerMethod.getMethodAnnotation(AuthCheck.class);
        if (authCheck == null) {
            if (!sessionVerify(request)) {
                printNotLogin(response, request);
                return false;
            }
            StoreUser user = storeUserService.selectByPrimaryKey(userUtils.getSessionUser().getId());
            if (!sessionVerifyStore(request,user)) {
                printStoreMessage(response, user);
                return false;
            }
        }

        return true;
    }

    private boolean sessionVerifyStore(HttpServletRequest request, StoreUser user) {
        HttpSession session = request.getSession();
        if (user.getDelFlag() == Constants.NOT_DEL
                && user.getEnableStatus() != Constants.ENABLE_STATUS_INVALID) {
            logger.info(user.getStoreId() + "门店状态正常");
            return true;
        } else {
            logger.info("门店状态异常");
            UserUtils.clearSession(session);
            return false;
        }
    }

    private void printStoreMessage(HttpServletResponse response,StoreUser user) {
        PrintWriter out = null;
        Map<String, Object> result = new HashMap<String, Object>();
        if (user.getDelFlag() == Constants.IS_DEL) {
            result.put("code", ResultCodeEnum.STORE_IS_DEL.getCode());
            result.put("msg", ResultCodeEnum.STORE_IS_DEL.getMsg());
        }
        if (user.getEnableStatus() == Constants.ENABLE_STATUS_INVALID) {
            result.put("code", ResultCodeEnum.STORE_IS_STOP.getCode());
            result.put("msg", ResultCodeEnum.STORE_IS_STOP.getMsg());
        }
        try {
            response.setContentType("application/json;charset=utf-8");
            out = response.getWriter();
            out.print(JSON.toJSON(result));
        } catch (IOException e) {
            logger.error("获取PrintWriter异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private boolean sessionVerify(HttpServletRequest request) {
        HttpSession session = request.getSession();
        StoreUser user = (StoreUser) session.getAttribute(Constants.SESSION_LOGIN_USER);
        if (null != user) {
            return true;
        } else {
            UserUtils.clearSession(session);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }

    private void printNotLogin(HttpServletResponse response, HttpServletRequest request) {
        PrintWriter out = null;
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", ResultCodeEnum.NOT_LOGIN.getCode());
        result.put("msg", ResultCodeEnum.NOT_LOGIN.getMsg());
        try {
            response.setContentType("application/json;charset=utf-8");
            out = response.getWriter();
            out.print(JSON.toJSON(result));
        } catch (IOException e) {
            logger.error("获取PrintWriter异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
