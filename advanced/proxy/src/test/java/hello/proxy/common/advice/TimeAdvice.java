package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        log.info("TimeAdvice 실행");
        long startTime = System.currentTimeMillis();

        // 로직
        invocation.proceed();

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        log.info("TimeAdvice 종료, Time = {} ms", totalTime);

        return null;
    }
}
