package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.setPerkDoubleRoll;

public class Quiz2 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    private int correctQuestions = 0;
    private final Player playerObj;

    public Quiz2(Player player) {
        this.playerObj = player;
    }
    @Override
    public boolean play() {
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "What is the value of the square root of 169?", "13");
        correctQuestions += askQuestion("\nGeography Question:", "What is the capital city of Australia?", "canberra");
        correctQuestions += askQuestion("\nHistory Question:", "In which year did World War II end?", "1945");
        correctQuestions += askQuestion("\nScience Question:", "Which planet is known as the Red Planet?", "mars");
        correctQuestions += askQuestion("\nInformatics Question:", "What is the most commonly used programming language for web development?", "javascript");
        correctQuestions += askQuestion("\nSports Question:", "Who holds the record for the most Olympic gold medals? (Hint: Swimmer)", "michael phelps");

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
