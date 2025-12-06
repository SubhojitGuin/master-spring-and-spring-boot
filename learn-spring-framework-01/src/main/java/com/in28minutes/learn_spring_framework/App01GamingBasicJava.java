package com.in28minutes.learn_spring_framework;

import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.PacManGame;
import com.in28minutes.learn_spring_framework.game.GamingConsole;

public class App01GamingBasicJava {
    public static void main(String[] args) {
//        GamingConsole game = new MarioGame();
//        GamingConsole game = new SuperContraGame();
        GamingConsole game = new PacManGame(); //1: Object Creation

        GameRunner gameRunner = new GameRunner(game);
            //2: Object Creation + Wiring of Dependencies
            //game is a dependency of GameRunner, so game is injected to GameRunner
        gameRunner.run();
    }
}
