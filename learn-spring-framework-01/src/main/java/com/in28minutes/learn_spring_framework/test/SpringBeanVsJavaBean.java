package com.in28minutes.learn_spring_framework.test;

import java.io.Serializable;

// Plain Old Java Object(POJO)
// Any Java object is a POJO
// No constraints
class Pojo {
    private String text;

    private int number;

    public String toString() {
        return text + ":" + number;
    }
}

//EJB - Enterprise java Bean
//1: The class should be Serializable
class JavaBean implements Serializable {
    private String text;
    private int number;

    //2:  It should have a public default(no-argument) constructor
    public JavaBean() {

    }

    //3:  It should have getters and setters to access the properties
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

// Spring Bean
// The spring context stores all the spring beans.
// All the beans managed by the Spring container (Bean Factory or Application Context) is called a Spring Bean

public class SpringBeanVsJavaBean {
    public static void main(String[] args) {
        Pojo pojo = new Pojo();

        System.out.println(pojo);
    }
}
