package com.glsx.vasp.beans;

import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.text.ParseException;
import java.util.Date;

/**
 * 各模块都需要的 Bean 配置
 */
@Configuration
public class CommonBeansConfig {

    final String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S"};
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CommonBeansConfig.class);

    @Bean
    public Converter<String, Date> addDateConvertor() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                try {
                    return org.apache.commons.lang.time.DateUtils.parseDate(source, parsePatterns);
                } catch (ParseException e) {
//                    e.printStackTrace();
                    logger.error("日期解析错误,当前日期为:" + source);
                }
                return null;
            }
        };
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.ofMegabytes(10));
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(10));
        return factory.createMultipartConfig();
    }

}
