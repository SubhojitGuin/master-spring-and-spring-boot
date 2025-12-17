package com.in28minutes.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {

    @Pointcut("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))")
    public void businessAndDataPackageConfig() {}

    @Pointcut("execution(* com.in28minutes.learn_spring_aop.aopexample.business.*.*(..))")
    public void businessPackageConfig() {}

    @Pointcut("execution(* com.in28minutes.learn_spring_aop.aopexample.data.*.*(..))")
    public void dataPackageConfig() {}

    // Refers to the beans with Service in their names - intercept based on the bean name
    @Pointcut("bean(*Service*)")
    public void allPackageConfigUsingBean() {}
}
