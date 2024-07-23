package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    // pointcut && advice
    @Around("execution(* hello.proxy.app..*(..))")
    public Object execute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 부가 기능(advice)
        TraceStatus status = null;
        try {
            status = logTrace.begin(proceedingJoinPoint.getSignature()
                                            .toShortString());
            // 로직 실행
            Object result = proceedingJoinPoint.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
