package com.in28minutes.learn_spring_aop.aopexample.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Target only methods
@Retention(RetentionPolicy.RUNTIME) // During runtime
public @interface TrackTime {
}
