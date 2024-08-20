package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;

public class Quiz7 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    public boolean play(Player p) {

        int correctQuestions=0;

        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "What is the integral of cos(x)?", "sinx", "sin(x)");
        correctQuestions += askQuestion("\nGeography Question:", "In which country is the southernmost capital located?", "new zealand");
        correctQuestions += askQuestion("\nHistory Question:", "Who is the Italian painter who painted The Last Supper?", "leonardo da vinci", "da vinci", "leonardo");
        correctQuestions += askQuestion("\nScience Question:", "What is the chemical symbol for Magnesium?", "mg");
        correctQuestions += askQuestion("\nInformatics Question:", "Is Python a compiled or interpreted language?", "interpreted");
        correctQuestions += askQuestion("\nSports Question:", "Which football team has won the most UEFA Champions League titles?", "real madrid");

        return evaluateQuiz(p, correctQuestions);
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

    private boolean evaluateQuiz(Player p, int correctQuestions) {
        if (correctQuestions == 6) {
            System.out.println("\nCongrats, you won the mini-game!!!");
            QuizReturnPoints.returnPoints(50, p);
            if(p.getPerkUtil().hasPerkBoostRoll()){
                System.out.println("You already have a 'Boost Roll' perk. No perk will be assigned.");
            }else{
                System.out.println("You obtained a 'Boost Roll' perk");
                p.getPerkUtil().setPerkBoostRoll(true);
            }
            return true;
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            p.getPerkUtil().setPerkBoostRoll(false);
            return false;
        }
    }
}