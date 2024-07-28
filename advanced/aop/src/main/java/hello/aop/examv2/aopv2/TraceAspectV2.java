package hello.aop.examv2.aopv2;

import hello.aop.examv2.annotationv2.TraceV2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspectV2 {


    @Before("@annotation(traceV2)")
    public void traceLog(JoinPoint joinPoint, TraceV2 traceV2) {
        Object[] args = joinPoint.getArgs();
        log.info("[trace] log : {} / {}", joinPoint.getSignature(), args);
    }

}
