package com.glsx.vasp.admin.common.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.glsx.vasp.exception.SystemMessage;
import com.glsx.vasp.utils.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        // If the subject isn't identified, redirect to login URL
        if (subject.getPrincipal() == null) {
            saveRequestAndRedirectToLogin(request, response);
        } else {
            //如果为 ajax 请求
//            String userAgentString = ((HttpServletRequest) request).getHeader("User-Agent");
//            if (org.apache.commons.lang.StringUtils.isNotBlank(userAgentString)) {
//                UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
//                DeviceType deviceType = userAgent.getOperatingSystem().getDeviceType();
//                String deviceTypeName = deviceType.getName();
//                if ("computer".equalsIgnoreCase(deviceTypeName)) {
//
//                }
//            }
            String header = ((HttpServletRequest) request).getHeader("x-requested-with");
            if ((StringUtils.isNotBlank(header) && "XMLHttpRequest".equalsIgnoreCase(header))) {
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(JSONObject.toJSONString(SystemMessage.PERMISSION_DENIED.result()));
                return false;
            }
            super.onAccessDenied(request, response);
        }
        return false;
    }
}
