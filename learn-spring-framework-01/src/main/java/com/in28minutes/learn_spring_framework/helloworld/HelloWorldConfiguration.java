package com.in28minutes.learn_spring_framework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Address(String firstLine, String city) { }
record Person(String name, int age, Address address) { }

// Configure the things we want Spring to manage - @Configuration
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String name() {
        return "Ranga";
    }

    @Bean
    public int age() {
        return 21;
    }

    @Bean
    public Person person() {
        return new Person("Ravi", 22, new Address("31 Baker Street", "Nevada"));
    }

    // Utilize the existing Beans to create a new Bean - two ways
    // 1st way: Direct Method Call to the existing Beans
    @Bean
    public Person person2MethodCall() {
        return new Person(name(), age(), address());
    }

    // 2nd way: Parameter Injection (More preferable)
    @Bean
    public Person person3Parameters(String name, int age, Address address3) { // Here, we use the Bean name - address3
        return new Person(name, age, address3);
    }

    // Here address is resolved by using the class name - Address
    // But, there are multiple candidates (Beans with the same type), which causes conflict
    // To resolve this, use @Primary for higher priority
    @Bean
    @Primary
    public Person person4Parameters(String name, int age, Address address) {
        return new Person(name, age, address);
    }

    // Multiple candidates can also be resolved using @Qualifier
    // The address will use the address3 using the qualifier mentioned
    @Bean
    public Person person5Qualifier(String name, int age, @Qualifier("address3qualifier") Address address) {
        return new Person(name, age, address);
    }

    @Bean(name = "address2")
    @Primary
    public Address address() {
        return new Address("26A South-West Road", "Ohio");
    }

    @Bean(name = "address3")
    @Qualifier("address3qualifier")
    public Address address3() {
        return new Address("Motinagar", "Hyderabad");
    }
}
