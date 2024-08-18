package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;

public class Quiz5 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    //private int correctQuestions = 0;
    //private final Player playerObj;

    /*public Quiz5(Player player) {
        this.playerObj = player;
    }*/
    @Override
    public boolean play(Player p) {

        int correctQuestions=0;

        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath Question:", "What is the value of pi rounded to two decimal places?", "3.14");
        correctQuestions += askQuestion("\nGeography Question:", "What is the capital city of Japan?", "tokyo");
        correctQuestions += askQuestion("\nHistory Question:", "Which civilization built the pyramids?", "egyptians");
        correctQuestions += askQuestion("\nScience Question:", "What is the chemical symbol for the element oxygen?", "o");
        correctQuestions += askQuestion("\nInformatics Question:", "What does 'CPU' stand for?", "central processing unit");
        correctQuestions += askQuestion("\nSports Question:", "Which sport is known as the 'king of sports'?", "soccer", "football");

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
            if(p.getPerkUtil().hasPerkDoubleRoll()){
                System.out.println("You already have a 'Double Roll' perk. No perk will be assigned.");
            }else{
                System.out.println("You obtained a 'Double Roll' perk");
                p.getPerkUtil().setPerkDoubleRoll(true);
            }
            return true;
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            p.getPerkUtil().setPerkDoubleRoll(false);
            return false;
        }
    }
}
