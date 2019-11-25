package com.glsx.vasp.admin.common.config;

import com.glsx.vasp.admin.common.config.shiro.CustomFormAuthenticationFilter;
import com.glsx.vasp.admin.common.config.shiro.CustomPermissionsAuthorizationFilter;
import com.glsx.vasp.admin.common.config.shiro.SysUserRealm;
import com.glsx.vasp.admin.common.utils.KryoShiroRedisSerializer;
import com.glsx.vasp.admin.modules.Constants;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.crazycake.shiro.serializer.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${session.maxAge:43200}")
    private int maxAge;
    @Value("${global.session.timeout:43200000}")
    private long globalSessionTimeout;

    @Bean
    public Realm realm() {
        //设置加密方式
        SysUserRealm shiroRealm = new SysUserRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * 加密
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Constants.HASH_ALGORITHM);//散列算法
        hashedCredentialsMatcher.setHashIterations(Constants.HASH_ITERATIONS);//散列的次数
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);//转化为16进制(与入库时保持一致)
        return hashedCredentialsMatcher;
    }

    @Bean
    public IRedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setDatabase(database);
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    @Bean
    public RedisCacheManager cacheManager(IRedisManager redisManager) {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager);
        cacheManager.setExpire(maxAge);
        cacheManager.setKeyPrefix("dvasp:shiro:user");

        cacheManager.setKeySerializer(new StringSerializer());
        cacheManager.setValueSerializer(new KryoShiroRedisSerializer());
        return cacheManager;
    }

    @Bean
    public SessionDAO sessionDAO(IRedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        //修改 Cookie 名，避免与SERVLET容器（如JETTY, TOMCAT）默认的Cookie名（JSESSIONID）冲突
        Cookie cookie = new SimpleCookie("DVASP.SESSION.ID");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(cookie);
        sessionManager.setGlobalSessionTimeout(globalSessionTimeout);
        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;
    }

    /**
     * 安全权限管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager(Realm realm, CacheManager cacheManager, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }


    /**
     * shiro 过滤器
     * <p>
     * perms 规则:
     * 如果用户无授权,是有权限的
     * 如果用户有授权,必须所有第一级子串(以 : 分隔)包含路径配置所有对应子串 或 用户子串为 *
     * 如果用户授权串多于路径子串,则用户子串多出的部分必须为 * ; 否则无权限
     * <p>
     * perms[1] 表示用户第一级子串要么是 * 要么包含 1; 用户组织架构级别必须是 1 才能看乘客管理
     * <p>
     * 后期如果需要做细粒度的控制,需要在角色表增加权限字符串配置; 由前端界面管理
     * 权限字符串为 :
     * 102:*:*:*:*:* 表明对 102 所有资源都有权限
     * 102:10201,10202:*:*:* 表明对 102下 10201,10202 所有资源有权限
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        shiroFilterFactoryBean.setUnauthorizedUrl("aaa.html");
        //过滤器列表
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 不拦截  swaggerui
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/webjars/**", "anon");


        filterMap.put("/statics/**", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/**", "authc");

        //api
        filterMap.put("/api/**", "anon");

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc", new CustomFormAuthenticationFilter());
        filters.put("perms", new CustomPermissionsAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


//     /**
//     * 限制同一账号登录同时登录人数控制
//     *
//     * @return
//     */
//    @Bean
//    public KickoutSessionControlFilter kickoutSessionControlFilter() {
//        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
//        kickoutSessionControlFilter.setCacheManager(cacheManager());
//        kickoutSessionControlFilter.setSessionManager(sessionManager());
//        kickoutSessionControlFilter.setKickoutAfter(false);
//        kickoutSessionControlFilter.setMaxSession(1);
//        kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
//        return kickoutSessionControlFilter;
//    }

}
