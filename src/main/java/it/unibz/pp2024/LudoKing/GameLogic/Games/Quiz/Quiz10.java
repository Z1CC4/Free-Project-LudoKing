package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkBoostRoll;

public class Quiz10 extends MiniGame {

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
        System.out.println("What is the square root of 225?");
        String answer = sc.nextLine();
        return answer.contains("15");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("What is the largest island in the Mediterranean Sea?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("sicily");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("In which year did the French Revolution start?");
        String answer = sc.nextLine();
        return answer.contains("1789");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("Which organs in the human body is primarily responsible for filtering blood?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("kidney") || answer.toLowerCase().contains("kidneys");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("How many bytes does a GigaByte contain?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("one billion") || answer.toLowerCase().contains("1 billion");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("Which sport uses terms like \"eagle,\" \"birdie,\" and \"bogey\"?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("golf");
    }
}
