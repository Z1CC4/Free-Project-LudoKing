package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;

public class Quiz9 implements MiniGameInterface {

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
            hasPerkBoostRoll();
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
        }
    }

    private boolean askQuestion1() {
        System.out.println("\nMath question:");
        System.out.println("If a train travels at a speed of 80 kilometers per hour for 2.5 hours, how far does it travel?");
        String answer = sc.nextLine();
        return answer.contains("200 kilometers") || answer.contains("200 km");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("Which country is known as the 'Land of the Rising Sun'?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("japan");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("What was the name of the ancient Greek city-state known for its military prowess and system of government?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("sparta");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("What is the name of the process by which plants convert sunlight into energy?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("photosynthesis") || answer.toLowerCase().contains("chlorophyll photosynthesis") || answer.toLowerCase().contains("photosyntesis") || answer.toLowerCase().contains("photosintesis");
    }

    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("What does the acronym USB stand for?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("universal serial bus");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("Who won the UEFA Euro 2020 (held in 2021)?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("italy");
    }
}
