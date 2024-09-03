package it.unibz.pp2024.LudoKing.gameLogic.games.miniGames;

import it.unibz.pp2024.LudoKing.gameLogic.games.quiz.MiniGame;
import it.unibz.pp2024.LudoKing.user.Player;

import java.util.Random;
import java.util.Scanner;

public class NewGTW extends MiniGame {

    private static Random rand = new Random();
    Scanner sc = new Scanner(System.in);
    private static final int attemptsMax=20;
    private static final int secretWordLength=4;
    private static char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};

    private static String secretWord=generateSecretWord();

    public static void returnPoints(Player player) {
        int pointsToAdd = 100;
        System.out.println("You obtained: " + pointsToAdd + " points.");
        player.getPoints().addPoints(pointsToAdd);
    }

    @Override
    public boolean play(Player p) {
        return false;
    }

    public static void game(){

    }

    public static String generateSecretWord(){
        String name="";
        for(int i=0;i<=secretWordLength;i++){
            name+=characters[rand.nextInt(0,characters.length)];
        }
        return name;
    }
}
