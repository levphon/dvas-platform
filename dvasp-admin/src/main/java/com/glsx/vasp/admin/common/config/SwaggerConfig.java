package com.glsx.vasp.admin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Docket createRestApi() throws UnknownHostException {
//	 	ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        tokenPar.name("token").description("令牌")
//        .modelRef(new ModelRef("string")).parameterType("query").required(false).build();
//        pars.add(tokenPar.build());

        List<Parameter> pars = new ArrayList<Parameter>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        Parameter build = tokenPar.name("channel").description("访问渠道")
                .allowableValues(new AllowableListValues(Arrays.asList("manager"), "string"))
                .modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        pars.add(build);

//        String model = appName.split("-")[1];

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.glsx.vasp.modules.*.entity"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() throws UnknownHostException {
        String hostAddress = Inet4Address.getLocalHost().getHostAddress();

        String serviceUrl = "http://" + hostAddress + ":" + port + "/";

        return new ApiInfoBuilder()
                .title("后台管理模块")
                .description("后台管理模块")
                .contact(new Contact(appName, serviceUrl + "swagger-ui.html", "operation@didihu.com.cn"))
                .termsOfServiceUrl(serviceUrl)
                .version("1.0")
                .build();
    }
}
