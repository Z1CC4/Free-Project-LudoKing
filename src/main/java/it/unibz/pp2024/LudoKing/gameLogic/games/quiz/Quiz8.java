package it.unibz.pp2024.LudoKing.gameLogic.games.quiz;

import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Points;

import java.util.Scanner;

public class Quiz8 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    public boolean play(Player p) {

        int correctQuestions=0;

        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "What is the value of 5 factorial (5!)?", "120");
        correctQuestions += askQuestion("\nGeography Question:", "Which continent is the least populated?", "antarctica");
        correctQuestions += askQuestion("\nHistory Question:", "What was the number of the 'Apollo' mission, which was the first successful manned mission to the Moon?", "11", "eleven");
        correctQuestions += askQuestion("\nScience Question:", "Who is credited with formulating the laws of motion and universal gravitation?", "isaac newton", "newton");
        correctQuestions += askQuestion("\nInformatics Question:", "In operating systems, what does the acronym FIFO stand for?", "first in first out", "first in, first out");
        correctQuestions += askQuestion("\nSports Question:", "How many players are on a standard volleyball team on the court at one time?", "6", "six");

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
            if(p.hasPerkBoostRoll()){
                System.out.println("You already have a 'Boost Roll' perk. No perk will be assigned.");
            }else{
                System.out.println("You obtained a 'Boost Roll' perk");
                p.setPerkBoostRoll(true);
            }
            return true;
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
            p.setPerkBoostRoll(false);
            return false;
        }
    }
}
