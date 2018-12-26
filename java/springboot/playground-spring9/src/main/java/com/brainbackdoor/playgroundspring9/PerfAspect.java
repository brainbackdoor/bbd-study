package com.brainbackdoor.playgroundspring9;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerfAspect {

//    @Around("execution(* com.brainbackdoor..*.EventService.*(..))")//pointcut 표현식
    @Around("@annotation(PerfLogging)") // Annoation을 활용한 방법
//    @Around("bean(simpleEventService)") // bean을 활용한 방식
    // 이 어드바이스를 어떻게 정의할 것인가..
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed(); // 메소드 호출
        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }

    // before도 있음!!
    /*
        @Before
        @AfterReturning
        @AfterThrowing
        @Around
     */
    @Before("bean(simpleEventService)")
    public void hello(){
        System.out.println("hello");
    }
}
