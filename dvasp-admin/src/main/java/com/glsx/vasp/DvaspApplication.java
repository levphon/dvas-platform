package com.glsx.vasp;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.glsx.vasp.admin.common.config.ImportBeansConfigs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jmx.support.RegistrationPolicy;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ImportResource(locations = {"classpath:config/spring-dubbo.xml"})
@Import({ImportBeansConfigs.class})
@MapperScan({"com.glsx.vasp.admin.modules.*.mapper"})
@ComponentScan(value = {"com.glsx.vasp.admin", "com.glsx.vasp.framework.components"})
@EnableDubbo(scanBasePackages = "com.glsx.biz")
@EnableEurekaClient
@EnableFeignClients
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class DvaspApplication {
    public static void main(String[] args) {
        SpringApplication.run(DvaspApplication.class, args);
    }
}
