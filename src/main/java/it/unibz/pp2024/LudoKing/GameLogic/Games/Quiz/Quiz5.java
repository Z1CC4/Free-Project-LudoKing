package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDoubleRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkDoubleRoll;

public class Quiz5 extends MiniGame{
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
        System.out.println("What is the sum of the interior angles of a pentagon?");
        String answer = sc.nextLine();
        return answer.contains("540");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("Which important river flows through Cairo?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("nile");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("Where was Napoleon exiled before dying?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("helena") || answer.toLowerCase().contains("saint helena island");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("What is the chemical symbol for the element commonly known as 'table salt'?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("nacl") || answer.toLowerCase().contains("sodium chloride");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("What does the HTTP stand for in website URLs?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("hypertext transfer protocol");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("Who holds the record for the fastest 100m sprint in the Olympics?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("usain bolt");
    }
}
