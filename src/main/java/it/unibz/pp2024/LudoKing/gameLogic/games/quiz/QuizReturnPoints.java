package it.unibz.pp2024.LudoKing.gameLogic.games.quiz;

import it.unibz.pp2024.LudoKing.user.Player;

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
