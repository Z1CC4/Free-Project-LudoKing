package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDoubleRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkDoubleRoll;

public class Quiz1 extends MiniGame {

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
            setPerkDoubleRoll(true);
            System.out.println("You obtained a 'Double Roll' perk");
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            setPerkDoubleRoll(false);
        }
    }

    private boolean askQuestion1() {
        System.out.println("\nMath question:");
        System.out.println("Solve the equation: x^2 − 7x + 10 = 0");
        String answer = sc.nextLine();
        return answer.contains("5") && answer.contains("2");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("What is the longest river in the world?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("amazon river");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("Who was the first President of the United States?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("george washington");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("What is the chemical symbol for gold?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("au");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("What does HTML stand for?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("hypertext markup language");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("In which sport do players try to knock down pins with a ball?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("bowling");
    }
}
