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
    // Joint Point - When a Pointcut condition is true, the advice is executed. A specific execution instance of Advice is called a Join Point.
    // Here, the reference points to the pointcut specified in the provided function name
    @Before("com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.allPackageConfigUsingBean()") //WHEN
    public void logMethodCallBeforeExecution(JoinPoint joinPoint) {
        logger.info("Before Aspect - {} is called with arguments: {}", joinPoint, joinPoint.getArgs()); //WHAT
    }

    // Executed after the method is executed for both cases - returns error AND runs successfully
    @After("com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.businessPackageConfig()")
    public void logMethodCallAfterExecution(JoinPoint joinPoint) {
        logger.info("After Aspect - {} had executed", joinPoint);
    }

    // Executed after the method has thrown an exception
    @AfterThrowing(
            pointcut = "com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.businessAndDataPackageConfig()",
            throwing = "exception"
    )
    public void logMethodCallAfterException(JoinPoint joinPoint, Exception exception) {
        logger.info("AfterThrowing Aspect - {} had thrown an exception {}", joinPoint, exception.getMessage());
    }

    // Executed after the method has successfully executed
    @AfterReturning(
            pointcut = "com.in28minutes.learn_spring_aop.aopexample.aspects.CommonPointcutConfig.dataPackageConfig()",
            returning = "resultValue"
    )
    public void logMethodCallAfterSuccessfulExecution(JoinPoint joinPoint, Object resultValue) {
        logger.info("AfterReturning Aspect - {} has returned {}", joinPoint, resultValue);
    }
}
