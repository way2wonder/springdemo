package com.feifan.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyInterceptor
{
    
    @Pointcut("execution (* com.feifan.business.service..*.*(..))")
    public void testPointCut() {} 
    
    //前置通知（不需要获取输入参数）
    @Before("testPointCut()")//第一个参数为切入点的名称
    public void doAccessCheck(){
        System.out.println("前置通知");
    }
    
    //后置通知(不需要获取返回值)
    @AfterReturning("testPointCut()")
    public void doAfterReturning(){
        System.out.println("后置通知:");
    }
    
    //例外通知(不需要异常信息)
    @AfterThrowing("testPointCut()")
    public void doAfterThrowing(){
        System.out.println("例外通知");
    }
    
    //最终通知
    @After("testPointCut()")
    public void doAfter(){
        System.out.println("最终通知");
    }
    
    @Around("testPointCut()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println(System.currentTimeMillis());
        Object object=pjp.proceed();
        System.out.println(System.currentTimeMillis());
        return object;
    }
}
