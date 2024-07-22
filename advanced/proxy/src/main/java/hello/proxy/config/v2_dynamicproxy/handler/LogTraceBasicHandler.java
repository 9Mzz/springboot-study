package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import jdk.dynalink.linker.MethodHandleTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.PrimitiveIterator;

@Slf4j
public class LogTraceBasicHandler implements InvocationHandler {

    private final Object   target;
    private final LogTrace logTrace;

    public LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target   = target;
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try {
            String methodName = method.getDeclaringClass()
                    .getSimpleName() + "." + method.getClass() + "()";
            status = logTrace.begin(methodName);
            // 로직 호출
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
