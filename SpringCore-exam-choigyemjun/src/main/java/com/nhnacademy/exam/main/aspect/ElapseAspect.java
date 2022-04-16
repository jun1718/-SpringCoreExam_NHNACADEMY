package com.nhnacademy.exam.main.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapseAspect {
    private Log log = LogFactory.getLog(ElapseAspect.class);

    @Around("execution(public * com.nhnacademy.exam.main.*.*..*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch(pjp.getSignature().getName());
        try {
            stopWatch.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }
    }
}
