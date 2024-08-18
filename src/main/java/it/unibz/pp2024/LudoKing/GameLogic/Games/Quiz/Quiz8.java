package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkBoostRoll;

public class Quiz8 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    private int correctQuestions = 0;
    private final Player playerObj;

    public Quiz8(Player player) {
        this.playerObj = player;
    }
    public boolean play() {
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "What is the value of 5 factorial (5!)?", "120");
        correctQuestions += askQuestion("\nGeography Question:", "Which continent is the least populated?", "antarctica");
        correctQuestions += askQuestion("\nHistory Question:", "What was the number of the 'Apollo' mission, which was the first successful manned mission to the Moon?", "11", "eleven");
        correctQuestions += askQuestion("\nScience Question:", "Who is credited with formulating the laws of motion and universal gravitation?", "isaac newton", "newton");
        correctQuestions += askQuestion("\nInformatics Question:", "In operating systems, what does the acronym FIFO stand for?", "first in first out", "first in, first out");
        correctQuestions += askQuestion("\nSports Question:", "How many players are on a standard volleyball team on the court at one time?", "6", "six");

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
