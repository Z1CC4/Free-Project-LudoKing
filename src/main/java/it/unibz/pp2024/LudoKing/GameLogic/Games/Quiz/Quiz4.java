package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDoubleRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkDoubleRoll;

public class Quiz4 extends MiniGame {

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
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            setPerkDoubleRoll(false);
        }
    }

    private boolean askQuestion1() {
        System.out.println("\nMath question:");
        System.out.println("What is the sum of the first ten prime numbers?");
        String answer = sc.nextLine();
        return answer.contains("129");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("Which desert is the largest hot desert in the world?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("sahara");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("In what year did Christopher Columbus first reach the Americas?");
        String answer = sc.nextLine();
        return answer.contains("1492");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("What is the most abundant gas in the Earth's atmosphere?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("nitrogen");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("Which programming language, commonly used for data analysis and machine learning, was named after a television show in the 1970s?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("python");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("Which tennis player holds the record for the most Grand Slam titles in men's singles as of 2023?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("rafael nadal") || answer.toLowerCase().contains("nadal");
    }
}
