package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkBoostRoll;

public class Quiz7 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    private int correctQuestions = 0;
    private final Player playerObj;

    public Quiz7(Player player) {
        this.playerObj = player;
    }
    public boolean play() {
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "What is the integral of cos(x)?", "sinx", "sin(x)");
        correctQuestions += askQuestion("\nGeography Question:", "In which country is the southernmost capital located?", "new zealand");
        correctQuestions += askQuestion("\nHistory Question:", "Who is the Italian painter who painted The Last Supper?", "leonardo da vinci", "da vinci", "leonardo");
        correctQuestions += askQuestion("\nScience Question:", "What is the chemical symbol for Magnesium?", "mg");
        correctQuestions += askQuestion("\nInformatics Question:", "Is Python a compiled or interpreted language?", "interpreted");
        correctQuestions += askQuestion("\nSports Question:", "Which football team has won the most UEFA Champions League titles?", "real madrid");

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