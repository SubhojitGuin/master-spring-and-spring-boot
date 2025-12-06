package com.in28minutes.learn_spring_framework;

import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.GamingConsole;
import com.in28minutes.learn_spring_framework.game.PacManGame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.in28minutes.learn_spring_framework.game")
public class App03GamingSpringBeans {
    // Since the Component path is provided, Spring automatically creates the
    // component(PacManGame) object and autowires it to gameRunner
    @Bean
    public GameRunner gameRunner(GamingConsole game) {
        return new GameRunner(game);
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(App03GamingSpringBeans.class)) {
            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        }
    }
}
