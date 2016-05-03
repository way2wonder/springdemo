package com.feifan.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;



/**
 * @author zhy
 *Aspects (classes annotated with @Aspect) may have methods 
 *and fields just like any other class. They may also contain 
 *pointcut, advice, and introduction (inter-type) declarations.
 *
 *
 *pointcut designators (PCD)</br>
 */
//@Aspect
public class NotVeryUsefulAspect
{
    /**
     * execution  is PCD .
     */
    @Pointcut("execution(* transfer(..))")// the pointcut expression
    private void anyOldTransfer() {}// the pointcut signature
    
    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {}

    @Pointcut("within(com.xyz.someapp.trading..*)")
    private void inTrading(){}

    @Pointcut("anyPublicOperation() && inTrading()")  //联合两个point-cut
    private void tradingOperation(){}
}
