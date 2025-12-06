package com.in28minutes.learn_spring_framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {
    private GamingConsole game;

    // To run a specific component use the @Qualifier to specify
    // Can refer to a class without explicitly specifying Qualifier on the class(Bean) by using
    // the class name with the first letter in small
    // class Name - SuperContraGame, qualifier - superContraGame
    public GameRunner(@Qualifier("superContraGame") GamingConsole game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Running game: " + game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
