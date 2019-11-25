package com.glsx.vasp.admin.modules;

import com.glsx.vasp.constant.BasicConstants;

public class Constants extends BasicConstants {

    /**
     * 登录用户在SESSION中的KEY值
     */
    public final static String SESSION_LOGIN_USER = "dvasp_current_user";

    /**
     * 密码加密
     */
    public final static String HASH_ALGORITHM = "MD5";
    public final static String SALT_SOURCE = "0ca175b98";
    public final static int HASH_ITERATIONS = 1024;

    /**
     * 平台账号体系用户来源-趣融
     */
    public static final String USER_SOURCE = "1001";

    /**
     * 系统超级管理员ID
     */
    public static final int SYS_SUPER_USER_ID = 1;

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG("M"),
        /**
         * 菜单
         */
        MENU("C"),
        /**
         * 按钮
         */
        BUTTON("F"),
        /**
         * 伪菜单
         */
        VIRTUAL_MENU("VC");

        private String value;

        MenuType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
