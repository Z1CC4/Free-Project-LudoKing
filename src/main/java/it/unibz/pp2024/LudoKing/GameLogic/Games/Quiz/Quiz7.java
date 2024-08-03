package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkBoostRoll;

public class Quiz7 extends MiniGame {

    private Scanner sc = new Scanner(System.in);
    private int correctQuestions = 0;
    
    public void play() {
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        if (askQuestion1()) correctQuestions++;
        if (askQuestion2()) correctQuestions++;
        if (askQuestion3()) correctQuestions++;
        if (askQuestion4()) correctQuestions++;
        if (askQuestion5()) correctQuestions++;
        if (askQuestion6()) correctQuestions++;

        if (correctQuestions == 6) {
            System.out.println("\nCongrats, you won the mini-game!!!");
            QuizReturnPoints.returnPoints(50);
            setPerkBoostRoll(true);
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            setPerkBoostRoll(false);
        }
    }

    private boolean askQuestion1() {
        System.out.println("\nMath question:");
        System.out.println("What is the integral of cos(x)?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("sinx") || answer.toLowerCase().contains("sin(x)");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("In which country is the southernmost capital located?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("new zealand");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("Who is the Italian painter who painted The Last Supper?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("leonardo da vinci") || answer.toLowerCase().contains("da vinci") || answer.toLowerCase().contains("leonardo");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("What is the chemical symbol for Magnesium?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("mg");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("Is Python a compiled or interpreted language?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("interpreted");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("Which football team has won the most UEFA Champions League titles?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("real madrid");
    }
}
