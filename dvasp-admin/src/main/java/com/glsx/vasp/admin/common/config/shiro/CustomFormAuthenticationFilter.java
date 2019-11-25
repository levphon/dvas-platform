package com.glsx.vasp.admin.common.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.glsx.vasp.exception.SystemMessage;
import com.glsx.vasp.utils.StringUtils;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义权限过滤器
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 未登录时调用
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (this.isLoginRequest(request, response)) {
            super.onAccessDenied(request, response);
        }

        //如果为 ajax 请求
        String userAgentString = ((HttpServletRequest) request).getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgentString)) {
            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            DeviceType deviceType = userAgent.getOperatingSystem().getDeviceType();
            String deviceTypeName = deviceType.getName();
            if ("computer".equalsIgnoreCase(deviceTypeName)) {
            }
        }

        String header = ((HttpServletRequest) request).getHeader("x-requested-with");
        if ((StringUtils.isNotBlank(header) && "XMLHttpRequest".equalsIgnoreCase(header))) {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(SystemMessage.NOT_LOGIN.result()));
            return false;
        }

        //执行其它请求
        return super.onAccessDenied(request, response);
    }
}
