package com.glsx.vasp.admin.common.utils;

import com.glsx.vasp.admin.common.exception.AdminException;
import com.glsx.vasp.admin.modules.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 */
public class ShiroUtils {

    /**
     * 加密算法
     */
    public final static String hashAlgorithmName = "SHA-256";

    /**
     * 循环次数
     */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 当前登录用户
     *
     * @return
     */
    public static SysUser currentUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getUserId() {
        return currentUser().getId();
    }

    /**
     * 获取 session
     *
     * @return
     */
    public static Session session() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            throw AdminException.create("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }

}
