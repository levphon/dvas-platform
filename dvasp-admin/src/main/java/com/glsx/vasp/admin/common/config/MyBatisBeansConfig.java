package com.glsx.vasp.admin.common.config;

import com.glsx.vasp.admin.common.config.dataperm.PermInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis Bean 配置
 */
@Configuration
public class MyBatisBeansConfig {
    @Bean
    public Interceptor interceptor() {
        return new PermInterceptor();
    }
}
