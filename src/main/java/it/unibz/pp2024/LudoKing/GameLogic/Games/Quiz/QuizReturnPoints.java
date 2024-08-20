package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

public class QuizReturnPoints {

    public static void returnPoints(int points, Player player) {
        player.getPoints().addPoints(points);
        System.out.println("You obtained: " + points + " points.");
    }

    /*public static void returnPoints(int points) {
        //Points.addPoints(points);
        System.out.println("You obtained: " + points + " points.");
    }*/
}
