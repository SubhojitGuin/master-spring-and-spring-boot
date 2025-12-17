package com.in28minutes.learn_spring_aop.aopexample.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

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
    @Before("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))") //WHEN
    public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
        logger.info("Before Aspect - {} is called with arguments: {}", joinPoint, joinPoint.getArgs()); //WHAT
    }

    // Executed after the method is executed for both cases - returns error AND runs successfully
    @After("execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))")
    public void logMethodCallAfterExecution(JoinPoint joinPoint) {
        logger.info("After Aspect - {} had executed", joinPoint);
    }

    // Executed after the method has thrown an exception
    @AfterThrowing(
            pointcut = "execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))",
            throwing = "exception"
    )
    public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
        logger.info("AfterThrowing Aspect - {} had thrown an exception {}", joinPoint, exception.getMessage());
    }

    // Executed after the method has successfully executed
    @AfterReturning(
            pointcut = "execution(* com.in28minutes.learn_spring_aop.aopexample.*.*.*(..))",
            returning = "resultValue"
    )
    public void logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint, Object resultValue) {
        logger.info("AfterReturning Aspect - {} has returned {}", joinPoint, resultValue);
    }
}
