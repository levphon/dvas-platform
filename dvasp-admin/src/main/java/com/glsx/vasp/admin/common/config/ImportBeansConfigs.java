package com.glsx.vasp.admin.common.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.glsx.vasp.admin.common.config.shiro.ShiroExceptionHandler;
import com.glsx.vasp.beans.CommonBeansConfig;
import com.glsx.vasp.framework.beans.FeignClientsConfig;
import com.glsx.vasp.framework.beans.RedisCacheConfig;
import com.glsx.vasp.framework.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonBeansConfig.class,
        FdfsClientConfig.class,
        FeignClientsConfig.class,
        MyBatisBeansConfig.class,
        RedisCacheConfig.class,
        ShiroExceptionHandler.class,
        GlobalExceptionHandler.class})
public class ImportBeansConfigs {

}
