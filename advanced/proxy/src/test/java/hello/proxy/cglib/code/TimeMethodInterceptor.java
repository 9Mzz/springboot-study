package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeMethodInterceptor 실행");
        long startTime = System.nanoTime();
        // 로직
        Object result = methodProxy.invoke(target, args);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        log.info("TimeMethodInterceptor 종료, result = {}, totalTime = {}", result, totalTime);


        return result;
    }
}
