package com.glsx.vasp.business.common.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.glsx.vasp.beans.CommonBeansConfig;
import com.glsx.vasp.framework.beans.FeignClientsConfig;
import com.glsx.vasp.framework.beans.RedisCacheConfig;
import com.glsx.vasp.framework.beans.RedisSessionConfig;
import com.glsx.vasp.framework.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonBeansConfig.class,
        FdfsClientConfig.class,
        FeignClientsConfig.class,
        RedisCacheConfig.class,
        GlobalExceptionHandler.class})
public class ImportBeansConfigs {

}
