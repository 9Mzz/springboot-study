package hello.aop.examv2.aopv2;

import hello.aop.examv2.annotationv2.RetryV2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspectV2 {


    @Around("@annotation(retryV2)")
    public Object retryAspect(ProceedingJoinPoint joinPoint, RetryV2 retryV2) throws Throwable {
        int       totalCount      = retryV2.value();
        Exception exceptionHolder = null;
        for (int currentCount = 1; currentCount <= totalCount; currentCount++) {
            try {
                Object result = joinPoint.proceed();
                log.info("[retry] {} / {}", currentCount, totalCount);
                return result;
            } catch (Exception e) {
                log.info("[exception] {} / {} }", currentCount, totalCount);
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }


}
