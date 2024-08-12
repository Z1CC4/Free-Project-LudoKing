package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkBoostRoll;

public class Quiz8 extends MiniGame {

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
            setPerkBoostRoll(true);
            System.out.println("You obtained a 'Boost roll' perk");
            return true;
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            setPerkBoostRoll(false);
            return false;
        }
    }

    private boolean askQuestion1() {
        System.out.println("\nMath question:");
        System.out.println("What is the value of 5 factorial (5!)?");
        String answer = sc.nextLine();
        return answer.contains("120");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("Which continent is the least populated?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("antarctica");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("What was the number of the 'Apollo' mission, which was the first successful manned mission to the Moon?");
        String answer = sc.nextLine();
        return answer.contains("11") || answer.toLowerCase().contains("eleven");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("Who is credited with formulating the laws of motion and universal gravitation?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("isaac newton") || answer.toLowerCase().contains("newton");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("In operating systems, what does the acronym FIFO stand for?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("first in first out") || answer.toLowerCase().contains("first in, first out");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("How many players are on a standard volleyball team on the court at one time?");
        String answer = sc.nextLine();
        return answer.contains("6") || answer.toLowerCase().contains("six");
    }
}
