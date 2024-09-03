package it.unibz.pp2024.LudoKing.gameLogic.games.miniGames;

import it.unibz.pp2024.LudoKing.gameLogic.games.quiz.MiniGame;
import it.unibz.pp2024.LudoKing.user.Player;

import java.util.Random;
import java.util.Scanner;

public class NewGTW extends MiniGame {
    public static void returnPoints(Player player) {
        int pointsToAdd = 100;
        System.out.println("You obtained: " + pointsToAdd + " points.");
        player.getPoints().addPoints(pointsToAdd);
    }

    @Override
    public boolean play(Player p) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        return false;
    }
}
