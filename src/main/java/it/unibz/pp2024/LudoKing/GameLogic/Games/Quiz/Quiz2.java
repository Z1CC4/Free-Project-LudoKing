package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkDoubleRoll;

public class Quiz2 extends MiniGame {

    private Scanner sc = new Scanner(System.in);
    private int correctQuestions = 0;

    public boolean play() {
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
            setPerkDoubleRoll(true);
            System.out.println("You obtained a 'Double Roll' perk");
            return true;
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            setPerkDoubleRoll(false);
            return false;
        }
    }

    private boolean askQuestion1() {
        System.out.println("\nMath question:");
        System.out.println("What is the value of the square root of 169?");
        String answer = sc.nextLine();
        return answer.contains("13");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("What is the capital city of Australia?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("canberra");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("In which year did World War II end?");
        String answer = sc.nextLine();
        return answer.contains("1945");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("Which planet is known as the Red Planet?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("mars");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("What is the most commonly used programming language for web development?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("javascript");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("Who holds the record for the most Olympic gold medals?");
        System.out.println("Hint: Swimmer");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("michael phelps");
    }
}
