package com.in28minutes.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.aspectj.lang.annotation.Aspect;

@Configuration
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // This is a Pointcut
    // This is an execution Pointcut. It gets executed before every method call in all the classes in the specified package
    // format - execution(* PACKAGE.*.*(..))
    // * : return type (* - all)
    // PACKAGE : package where Aspect will work
    // * : class name (* - all)
    // * : method names (* - all)
    // (..) : method arguments
    @Before("execution(* com.in28minutes.learn_spring_aop.aopexample.business.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        logger.info("Before Aspect - {} is called with arguments: {}", joinPoint, joinPoint.getArgs());
    }
}
