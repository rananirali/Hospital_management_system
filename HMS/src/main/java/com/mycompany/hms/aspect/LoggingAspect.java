package com.mycompany.hms.aspect;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class LoggingAspect {
    
    @Around("execution(* com.mycompany.hms.helper.DBService.*(..))")
    public Object DBMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[LOG] Method start: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        System.out.println("[LOG] Method end: " + joinPoint.getSignature().getName());
        return result;
    }
}