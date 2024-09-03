package it.unibz.pp2024.LudoKing.gameLogic.games.miniGames;

import it.unibz.pp2024.LudoKing.gameLogic.games.quiz.MiniGame;
import it.unibz.pp2024.LudoKing.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NewGTW extends MiniGame {

    private static Random rand = new Random();
    private static Scanner sc = new Scanner(System.in);
    private static int attempts=20;
    private static final int secretWordLength=4;
    private static char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};

    private static String secretWord=generateSecretWord();

    private static List<Character> lettersToBuy=new ArrayList<>();
    private static String boughtLetters="";

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
        String choice="";
        boolean win=false;
        System.out.println("Welcome to the guess the word game.");
        while(attempts!=0 && !win){
            System.out.println("Type 'help' for additional information.");
            System.out.print("Attempt n:"+attempts);
            System.out.println();
            menu();
            System.out.println();
        }
        if(!win){
            System.out.println("You have finished all your attempts.");
        }else
            System.out.println("Congratulations. You guess the secret word.");
    }

    public static void menu(){
        System.out.println("1. Try to guess the secret word.");
        System.out.println("2. 'Help.'");
        System.out.println("3. 'Buy one letter.(You will lose 5 attempts)'");
        System.out.println("4. Show history of all guesses and evaluations.");
        System.out.print("-->");
        choiceMenu(Player.checkValidInput());
    }

    public static void evaluation(String s){

    }

    public static void guessSecretWord(){
        System.out.println("Type your guess.");
        System.out.print("-->");
        String guess=sc.next();
        while(!checkValidity(guess)){
            guess=sc.next();
        }

    }

    public static void buyLetter(){
        if(attempts==5){
            System.out.println("Are you sure you want to buy a letter?");
            System.out.println("The game will end since you will finish all the attempts");
        }

        attempts-=5;
        boughtLetters+=lettersToBuy.remove(0);
    }

    public static void showHistory(){

    }

    public static void choiceMenu(int choice){
        switch(choice){
            case 1:
                guessSecretWord();
                break;
            case 2:
                System.out.println();
                System.out.println("-Your goal is to guess the secret code which has 4 characters.");
                System.out.println(
                        "-The secret code is composed of the first 6 letters of the alphabet. Repetitions are allowed.");
                System.out.println("-You have to guess the secret code before the number of attempts becomes 0.");
                System.out.println(
                        "-If your choice has a different length than 4, then it will appear an error message on the screen.");
                System.out.println(
                        "-In case you enter something wrong, the number of attempts will not be decremented. So, don't worry.");
                System.out.println("-Key for the evaluation: ");
                System.out.println("X --> a character of your guess is in the right position ");
                System.out.println("- --> a character of your guess is in the wrong position");
                System.out.println("Example:");
                System.out.println("Evaluation for attempt n:18--> XX--");
                System.out.println(
                        "In this case, the evaluation shows that in your guess there are two characters at the correct position and other two characters at the wrong position.");
                System.out.println();
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Enter a valid choice please.");
        }
    }

    public static String generateSecretWord(){
        String name="";
        for(int i=0;i<=secretWordLength;i++){
            char c=characters[rand.nextInt(0,characters.length)];
            name+=c;
            lettersToBuy.add(c);
        }
        return name;
    }

    public static boolean checkValidity(String s){
        return s.length() == 4;
    }

}
