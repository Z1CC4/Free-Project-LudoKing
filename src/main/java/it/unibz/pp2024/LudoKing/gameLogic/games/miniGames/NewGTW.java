package it.unibz.pp2024.LudoKing.gameLogic.games.miniGames;

import it.unibz.pp2024.LudoKing.gameLogic.games.quiz.MiniGame;
import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Points;

import java.util.*;

public class NewGTW extends MiniGame {

    private final static Random rand = new Random();
    private final static Scanner sc = new Scanner(System.in);
    private static final int secretWordLength = 4;
    private final static char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};

    private int attempts;
    private char[] lettersToBuy;
    private String secretWord;
    private String[] historyGuess;
    private String[] historyEvaluation;
    private char[] boughtLetters;
    private int counter;
    private boolean win;

    public NewGTW(){
        this.attempts = 20;
        this.lettersToBuy = new char[secretWordLength];
        this.secretWord = generateSecretWord();
        this.historyGuess = new String[attempts];
        this.historyEvaluation = new String[attempts];
        this.boughtLetters =new char[]{'.', '.', '.', '.'};
        this.counter = 0;
        this.win = false;
    }

    @Override
    public boolean play(Player p) {
        System.out.println("Welcome to the guess the word game.");
        System.out.println("Type 'help' for additional information.");
        while (attempts != 0 && !win) {
            menu();
        }
        if (!win) {
            System.out.println("You have finished all your attempts. The secret word was:"+secretWord);
            return false;
        } else{
            System.out.println("Congratulations. You guess the secret word.");
            Points.returnPoints(100, p);
            if(p.hasPerkDoubleRoll()){
                System.out.println("You already have a 'Double Roll' perk. No perk will be assigned.");
            }else {
                System.out.println("You obtained a 'Double Roll' perk");
                p.setPerkDoubleRoll(true);
            }
            return true;
        }


    }



    public void menu() {
        System.out.print(attempts + ">");
        String choice = sc.next();
        if (!choiceMenu(choice)) {
            guess(choice);
        }
    }

    public void guess(String s) {
        String eval = "";
        while (!checkValidity(s)) {
            System.out.println("Your choice has length different than 4. Insert a guess of length 4.");
            System.out.print(attempts + ">");
            s = sc.next();
        }
        if (s.equalsIgnoreCase(secretWord)) {
            win = true;
        }
        boolean[] checked=new boolean[secretWordLength];
        eval+=checkLettersSamePosition(s, checked);
        eval+=checkLettersDifferentPosition(s, checked);
        historyGuess[counter] = s;
        if(!eval.isBlank()){
            System.out.println("Evaluation for you guess:"+eval);
            historyEvaluation[counter] = eval;
        }else{
            System.out.println("There are no matching letters.");
            historyEvaluation[counter] = "No matching letters";
        }
        counter++;
        attempts--;
    }

    public String checkLettersSamePosition(String currentGuess, boolean[] checked) {
        String eval="";
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == currentGuess.charAt(i)) {
                eval += 'X';
                checked[i]=true;
            }
        }
        return eval;
    }

    public String checkLettersDifferentPosition(String currentGuess, boolean[] checked) {
        String eval="";
        boolean[] checkedDiffPos=new boolean[secretWordLength];
        for(int i=0;i<secretWord.length();i++){
            if(!checked[i]){
                for(int j=0;j<currentGuess.length();j++){
                    if(i!=j && secretWord.charAt(i)==currentGuess.charAt(j) && !checked[j] && !checkedDiffPos[j]){
                        eval+='-';
                        checkedDiffPos[j]=true;
                        break;
                    }
                }
            }
        }
        return eval;
    }

    public void history(){
        int i=0, historyAttempt=20;
        if(attempts==20){
            System.out.println("No guesses and evaluations yet.");
        }else{
            System.out.println("History of past guesses and evaluations");
            while(i<historyGuess.length && historyGuess[i]!=null) {
                System.out.println("Attempt."+historyAttempt+":"+historyGuess[i]+" "+historyEvaluation[i]);
                i++;
                historyAttempt--;
            }
        }
    }

    public boolean choiceMenu(String choice){
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
                        choiceMade=true;
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



    public void buy(){
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

    public String boughtLettersToCart(){
        String cart="";
        for (char boughtLetter : boughtLetters) cart += boughtLetter;
        return cart;
    }

    public String generateSecretWord(){
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

}
