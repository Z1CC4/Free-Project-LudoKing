package it.unibz.pp2024.LudoKing.gameLogic.games.quiz;

import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Points;

import java.util.Scanner;

public class Quiz1 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    @Override
    public boolean play(Player p) {

        int correctQuestions=0;

        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "Solve the equation: x^2 − 7x + 10 = 0", "5", "2");
        correctQuestions += askQuestion("\nGeography Question:", "What is the longest river in the world?", "amazon river");
        correctQuestions += askQuestion("\nHistory Question:", "Who was the first President of the United States?", "george washington");
        correctQuestions += askQuestion("\nScience Question:", "What is the chemical symbol for gold?", "au");
        correctQuestions += askQuestion("\nInformatics Question:", "What does HTML stand for?", "hypertext markup language");
        correctQuestions += askQuestion("\nSports Question:", "In which sport do players try to knock down pins with a ball?", "bowling");

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
            Points.returnPoints(50, p);
            if(p.hasPerkDoubleRoll()){
                System.out.println("You already have a 'Double Roll' perk. No perk will be assigned.");
            }else{
                System.out.println("You obtained a 'Double Roll' perk");
                p.setPerkDoubleRoll(true);
            }
            return true;
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            p.setPerkDoubleRoll(false);
            return false;
        }
    }
}
