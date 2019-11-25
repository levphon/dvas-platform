package com.glsx.vasp.admin.common.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author payu
 * @create 2019/4/17 11:17
 * @since 1.0.0
 */
//@Configuration
public class DubboConfig {

    /**
     * 应用名称
     */
    @Value("${spring.dubbo.application.name}")
    private String applicationName;

    /**
     * 注册中心地址
     */
    @Value("${spring.dubbo.registry.address}")
    private String registryAddress;

    /**
     * 缓存文件地址
     */
//    @Value("${spring.dubbo.registry.file}")
//    private String registryFile;

    /**
     * dubbo协议端口号
     */
    @Value("${spring.dubbo.protocol.port}")
    private Integer protocolPort;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("estimate-web");
        return applicationConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(6000);
        return consumerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://192.168.3.206:2181");
        return registryConfig;
    }

}
