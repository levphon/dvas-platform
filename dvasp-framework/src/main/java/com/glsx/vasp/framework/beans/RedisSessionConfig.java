package com.glsx.vasp.framework.beans;

import org.springframework.context.annotation.Configuration;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * session 失效时间 无限制 分钟后失效
 */
@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = -1)
public class RedisSessionConfig {

//    @Bean
//    public CookieSerializer cookieSerializer(){
//        CookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        ((DefaultCookieSerializer) cookieSerializer).setUseBase64Encoding(false);
//        return cookieSerializer;
//    }

}
