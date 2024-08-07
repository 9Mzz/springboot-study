package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class TimeInvocationHandler implements java.lang.reflect.InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        // 로직
        Object result    = method.invoke(target, args);
        long   endTime   = System.currentTimeMillis();
        long   totalTime = endTime - startTime;
        log.info("TimeProxy 종료, result = {}, totalTime = {}", result, totalTime);

        return null;
    }
}
