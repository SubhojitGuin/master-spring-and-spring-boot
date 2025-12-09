package com.in28minutes.springboot.learn_jpa_and_hibernate.course.jdbc;

import com.in28minutes.springboot.learn_jpa_and_hibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository repository;

    @Override
    public void run(String ... args) throws Exception {
        repository.insert(new Course(1, "Learn AWS!", "Subho"));
        repository.insert(new Course(2, "Learn Azure!", "Subho"));
        repository.insert(new Course(3, "Learn DevOps!", "Subho"));

        repository.deleteById(1);
    }
}
