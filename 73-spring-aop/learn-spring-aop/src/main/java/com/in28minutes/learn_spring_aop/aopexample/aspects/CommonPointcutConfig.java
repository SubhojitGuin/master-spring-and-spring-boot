package com.in28minutes.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.annotation.Pointcut;

// BEST PRACTICE
// Maintain the set of common annotations to prevent duplications of the path and ensure
// that even if the package path changes there is need of changing only a single place
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

    @Pointcut("@annotation(com.in28minutes.learn_spring_aop.aopexample.annotations.TrackTime)")
    public void trackTimeAnnotation() {}
}
