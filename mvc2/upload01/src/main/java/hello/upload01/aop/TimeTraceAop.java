package hello.upload01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//implementation 'org.springframework.boot:spring-boot-starter-aop'

//@Aspect
//@Component
public class TimeTraceAop {

  @Around("execution(* hello.upload01..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    System.out.println("AOP START: " + joinPoint.toString());
    try {
      return joinPoint.proceed();
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("AOP END: " + joinPoint.toString() + " " + timeMs + "ms");
    }
  }
}