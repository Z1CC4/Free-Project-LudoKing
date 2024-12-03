package it.unibz.pp2024.LudoKing.gameLogic.games.quiz;

import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Points;

import java.util.Scanner;

public class Quiz10 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public boolean play(Player p) {

        int correctQuestions=0;

        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "What is the square root of 225?", "15");
        correctQuestions += askQuestion("\nGeography Question:", "What is the largest island in the Mediterranean Sea?", "sicily");
        correctQuestions += askQuestion("\nHistory Question:", "In which year did the French Revolution start?", "1789");
        correctQuestions += askQuestion("\nScience Question:", "Which organ in the human body is primarily responsible for filtering blood?", "kidney", "kidneys");
        correctQuestions += askQuestion("\nInformatics Question:", "How many bytes does a GigaByte contain?", "one billion", "1 billion");
        correctQuestions += askQuestion("\nSports Question:", "Which sport uses terms like \"eagle,\" \"birdie,\" and \"bogey\"?", "golf");

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
