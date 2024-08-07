package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

/*
    @Around("hello.aop.order.aop.PointCuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("트랜잭션 시작 = {}", joinPoint.getSignature());
            // 로직
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("트랜잭션 커밋 = {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("트랜잭션 롤백 = {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("트랜잭션 릴리즈 = {}", joinPoint.getSignature());
        }
    } */

    @Before("hello.aop.order.aop.PointCuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.PointCuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AfterReturning] {}, result = {} ", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.PointCuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[AfterThrowing] {}, Exception = {}", joinPoint.getSignature(), ex);
    }

    @After(value = "hello.aop.order.aop.PointCuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
