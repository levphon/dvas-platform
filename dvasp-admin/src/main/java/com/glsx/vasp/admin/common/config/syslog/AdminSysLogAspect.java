package com.glsx.vasp.admin.common.config.syslog;

import com.glsx.vasp.admin.common.utils.ShiroUtils;
import com.glsx.vasp.framework.components.SysLogAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdminSysLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(AdminSysLogAspect.class);

    @Value("${project.package.prefix:com.glsx}")
    private String packagePrefix;

    @Autowired
    private SysLogAspect sysLogAspect;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Pointcut("@annotation(com.glsx.vasp.annotation.SysLog)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void saveLog(JoinPoint joinPoint) {
//        HttpServletRequest request = SessionUtils.request();
//        String remortIP = IpUtils.getIpAddr(request);
        sysLogAspect.saveSysLog(joinPoint, ShiroUtils.currentUser());
    }

    /**
     * 打印方法执行时间
     */
    @Around("logPointCut()")
    public Object printExecutorTime(ProceedingJoinPoint jp) throws Throwable {
        return sysLogAspect.printExecutorTime(jp);
    }
}
