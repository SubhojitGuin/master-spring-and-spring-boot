package com.in28minutes.learn_spring_framework.examples.d1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class ClassA {

}

// Default initialization is Eager (More Recommended)
/**
 * Lazy Initialization
 * The @Lazy is used when you don't want to create the Bean immediately after the app is run
 * It is created when the Bean is required or called
 */
@Component
@Lazy
class ClassB {
    private ClassA classA;

    public ClassB (ClassA classA) {
        // Logic
        System.out.println("Some Initialization Logic");
        this.classA = classA;
    }

    public void doSomething() {
        System.out.println("Do something");
    }
}
@Configuration
@ComponentScan // Uses the current folder as the default path
public class LazyInitializationLauncherApplication {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(LazyInitializationLauncherApplication.class)) {
//            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println("Initialization of object is completed");

            // Here, the ClassB is used, so the Bean is created now instead at the start of application
            context.getBean(ClassB.class).doSomething();
        }
    }
}
