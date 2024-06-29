package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Points;

import java.util.Scanner;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDoubleRoll;

public class Quiz3 extends MiniGame {

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
            hasPerkDoubleRoll();
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
        }
    }

    private boolean askQuestion1() {
        System.out.println("\nMath question:");
        System.out.println("What is the value of the derivative of the function f(x) = 3x^2 + 5x - 7 at x = 2?");
        String answer = sc.nextLine();
        return answer.contains("17");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("Which country has the largest land area in Africa?");
        System.out.println("Hint: It is on the Mediterranean Sea");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("algeria");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("Who wrote the epic poem 'The Divine Comedy'?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("dante");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("What is the chemical symbol for the element with the atomic number 20, and which is essential for bone health?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("ca") || answer.toLowerCase().contains("calcium");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("What data structure is used to implement a Last In, First Out (LIFO) system?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("stack");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("Which country won the FIFA World Cup in 2018?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("france");
    }
}
