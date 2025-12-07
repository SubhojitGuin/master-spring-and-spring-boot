package com.in28minutes.learn_spring_framework.examples.e1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Scope - Singleton (Default)
 * Only one(same) instance of the Bean is returned everytime the Bean is referred
 * One object instance per IoC Container
 * Can be specified using @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
 */
@Component
class NormalClass {

}

/**
 * Scope - Prototype
 * A new instance of the Bean is created everytime the Bean is referred
 * Possibly many object instances per Spring IoC container
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class PrototypeClass {

}

@Configuration
@ComponentScan // Uses the current folder as the default path
public class BeanScopesLauncherApplication {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(BeanScopesLauncherApplication.class)) {
            // Refers to the same instance of the NormalClass
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));

            // Refers to different instances of the PrototypeClass for every call
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
        }
    }
}
