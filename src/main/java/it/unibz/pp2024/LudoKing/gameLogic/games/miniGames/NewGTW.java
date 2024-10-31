package it.unibz.pp2024.LudoKing.gameLogic.games.miniGames;

import it.unibz.pp2024.LudoKing.gameLogic.games.quiz.MiniGame;
import it.unibz.pp2024.LudoKing.user.Player;

import java.util.*;

public class NewGTW extends MiniGame {

    private static Random rand = new Random();
    private static Scanner sc = new Scanner(System.in);
    private static int attempts=20;
    private static final int secretWordLength=4;
    private static char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
    //private static List<Character> lettersToBuy=new ArrayList<>();
    private static char[] lettersToBuy=new char[secretWordLength];
    private static String secretWord=generateSecretWord();
    //private static List<String> historyGuess=new ArrayList<>();
    private static String[] historyGuess=new String[attempts];
    private static String[] historyEvaluation=new String[attempts];
    //private static String boughtLetters="....";
    //private static char[] boughtLetters=new char[secretWordLength];
    private static char[] boughtLetters={'.','.','.','.'};

    private static int counter=0;
    private static boolean win=false;



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
        System.out.println(secretWord);
        System.out.println("Welcome to the guess the word game.");
        System.out.println("Type 'help' for additional information.");
        while(attempts!=0 && !win){
            menu();
            System.out.println();
            //attempts--;
        }
        if(!win){
            System.out.println("You have finished all your attempts.");
        }else
            System.out.println("Congratulations. You guess the secret word.");
    }

    public static void menu(){
        System.out.print(attempts+">");
        String choice=sc.next();
        if(!choiceMenu(choice)){
            guess(choice);
        }
    }

    public static void guess(String s){
            while(!checkValidity(s)){
                System.out.println("Your choice has length different than 4. Insert a guess of length 4.");
                System.out.print(attempts+">");
                s=sc.next();
            }
            if(s.equalsIgnoreCase(secretWord)){
                win=true;
            }else{

            }
            historyGuess[counter]=s;
            counter++;
            attempts--;
    }

    public static void evaluation(){

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
    }

    public static void history(){
        System.out.println("History of past guesses and evaluations");
        int i=0, historyAttempt=20;
        while(i<historyGuess.length && historyGuess[i]!=null) {
            System.out.println("Attempt n."+historyAttempt+":"+historyGuess[i]+" "+historyEvaluation[i]);
            i++;
            historyAttempt--;
        }
    }

    public static boolean choiceMenu(String choice){
        //if(checkValidity(choice)){
        boolean choiceMade=false;
            switch(choice.toLowerCase()){
                case "help":
                    help();
                    choiceMade=true;
                    break;
                case "h":
                    history();
                    choiceMade=true;
                    break;
                case "buy":
                    if(attempts>5){
                        buy();
                        choiceMade=true;
                        attempts-=5;
                    }else if(attempts==5){
                        boolean lastBuy=false;
                        System.out.println("If you buy a letter, you are going to lose all your attempts left.");
                        System.out.println("Do you want to continue?");
                        System.out.println("Type '1' to continue, otherwise type '2' to stop.");
                        int choiceLastBuy=Player.checkValidInput();
                        while(!lastBuy){
                            switch(choiceLastBuy){
                                case 1:
                                    buy();
                                    lastBuy=true;
                                    attempts-=5;
                                    break;
                                case 2:
                                    lastBuy=true;
                                    break;
                                default:
                                    System.out.println("Insert either 1 or 2 to continue.");
                            }
                        }
                    }else{
                        System.out.println("You do not have enough attempts to buy a letter.");
                    }
                    break;
            }
            return choiceMade;
    }

    public static void help(){
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
    }


    public static void buy(){
        boolean valid=false;
        int index=rand.nextInt(0,3);
        while(!valid){
            index = rand.nextInt(0, 4);
            if(lettersToBuy[index]!=0) {
                valid=true;
            }
        }
        char toBuy=lettersToBuy[index];
        boughtLetters[index]=toBuy;
        lettersToBuy[index]=0;
        System.out.println("You have bought a letter!");
        System.out.println("Your cart:"+boughtLettersToCart());
    }

    public static String boughtLettersToCart(){
        String cart="";
        for(int i=0;i<boughtLetters.length;i++){
            cart+=boughtLetters[i];
        }
        return cart;
    }

    public static String generateSecretWord(){
        String name="";
        for(int i=0;i<=secretWordLength-1;i++){
            char c=characters[rand.nextInt(0,characters.length)];
            name+=c;
            lettersToBuy[i]=c;
        }
        return name;
    }

    public static boolean checkValidity(String s){
        return s.length() == 4;
    }

    public static void main(String[] args){
        game();
    }

}
