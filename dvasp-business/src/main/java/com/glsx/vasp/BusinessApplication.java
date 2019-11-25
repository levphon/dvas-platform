package com.glsx.vasp;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.glsx.vasp.business.common.config.ImportBeansConfigs;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ImportResource(locations = {"classpath:config/spring-dubbo.xml"})
@Import({ImportBeansConfigs.class})
@MapperScan("com.glsx.vasp.business.modules.*.mapper")
@ComponentScan(value = {"com.glsx.vasp.business",
        "com.glsx.vasp.framework.components",
        "com.glsx.vasp.entity"})
@EnableEurekaClient
@EnableFeignClients
@EnableDubbo(scanBasePackages = "com.glsx.biz")
@EnableTransactionManagement
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }

}
