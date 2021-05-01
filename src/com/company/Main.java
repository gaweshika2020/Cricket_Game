package com.company;

import controller.GameController;
import controller.Implementation.findOverGameController;

public class Main {

    public static void main(String[] args) {
        GameController controller=new findOverGameController();
        controller.playGame();
    }

}
