package hello.proxy.commonv2.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdviceV2 implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdviceV2 invoke");
        long sTime = System.nanoTime();
        // 로직
        Object proceed = invocation.proceed();
        long   eTime   = System.nanoTime();
        long   tTime   = eTime - sTime;
        log.info("TimeAdviceV2 invoke tTime:{} ms", tTime);

        return null;
    }
}
