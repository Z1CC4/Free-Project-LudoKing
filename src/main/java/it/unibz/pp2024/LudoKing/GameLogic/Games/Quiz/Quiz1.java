package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkDoubleRoll;

public class Quiz1 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    private int correctQuestions = 0;
    private final Player playerObj;

    public Quiz1(Player player) {
        this.playerObj = player;
    }
    @Override
    public boolean play() {
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "Solve the equation: x^2 − 7x + 10 = 0", "5", "2");
        correctQuestions += askQuestion("\nGeography Question:", "What is the longest river in the world?", "amazon river");
        correctQuestions += askQuestion("\nHistory Question:", "Who was the first President of the United States?", "george washington");
        correctQuestions += askQuestion("\nScience Question:", "What is the chemical symbol for gold?", "au");
        correctQuestions += askQuestion("\nInformatics Question:", "What does HTML stand for?", "hypertext markup language");
        correctQuestions += askQuestion("\nSports Question:", "In which sport do players try to knock down pins with a ball?", "bowling");

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
}
