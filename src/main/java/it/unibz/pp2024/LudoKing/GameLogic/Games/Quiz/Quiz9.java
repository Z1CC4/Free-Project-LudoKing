package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkBoostRoll;

public class Quiz9 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    private int correctQuestions = 0;
    private final Player playerObj;

    public Quiz9(Player player) {
        this.playerObj = player;
    }
    public boolean play() {
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "If a train travels at a speed of 80 kilometers per hour for 2.5 hours, how far does it travel?", "200 kilometers", "200 km");
        correctQuestions += askQuestion("\nGeography Question:", "Which country is known as the 'Land of the Rising Sun'?", "japan");
        correctQuestions += askQuestion("\nHistory Question:", "What was the name of the ancient Greek city-state known for its military prowess and system of government?", "sparta");
        correctQuestions += askQuestion("\nScience Question:", "What is the name of the process by which plants convert sunlight into energy?", "photosynthesis", "chlorophyll photosynthesis", "photosyntesis", "photosintesis");
        correctQuestions += askQuestion("\nInformatics Question:", "What does the acronym USB stand for?", "universal serial bus");
        correctQuestions += askQuestion("\nSports Question:", "Who won the UEFA Euro 2020 (held in 2021)?", "italy");

        return evaluateQuiz();
    }

    private int askQuestion(String questionCategory, String question, String... correctAnswers) {
        System.out.println(questionCategory);
        System.out.println(question);
        String answer = sc.nextLine().toLowerCase();
        for (String correctAnswer : correctAnswers) {
            if (answer.contains(correctAnswer.toLowerCase())) {
                return 1;
            }
        }
        return 0;
    }

    private boolean evaluateQuiz() {
        if (correctQuestions == 6) {
            System.out.println("\nCongrats, you won the mini-game!!!");
            QuizReturnPoints.returnPoints(50, playerObj);
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
}