package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        //1: Launch a Spring Context with the HelloWorldConfiguration

        try (AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
            //2:  Configure the things we want Spring to manage - @Configuration
            //HelloWorldConfiguration - @Configuration
            //name - @Bean

            //3: Retrieving Beans managed by Spring
            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
            System.out.println(context.getBean("person"));
            System.out.println(context.getBean("person2MethodCall"));
            System.out.println(context.getBean("person3Parameters"));
            System.out.println(context.getBean("address2")); // Bean name is used to refer to the Bean
            System.out.println(context.getBean(Address.class)); // Bean type is used to refer to the Bean
            System.out.println(context.getBean(Person.class));
            System.out.println(context.getBean("person5Qualifier"));

            // List all Beans managed by Spring Framework
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
