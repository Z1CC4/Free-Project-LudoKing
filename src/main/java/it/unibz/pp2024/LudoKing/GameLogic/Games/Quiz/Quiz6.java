package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;


public class Quiz6 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    public boolean play(Player p) {

        int correctQuestions=0;

        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "What is the derivative of the function f(x) = 70.5x^2?", "141x");
        correctQuestions += askQuestion("\nGeography Question:", "In which state is the northernmost McDonald's in the world located?", "finland");
        correctQuestions += askQuestion("\nHistory Question:", "In what year did the unification of Italy take place?", "1861");
        correctQuestions += askQuestion("\nScience Question:", "What is the transition from the solid to the liquid state called?", "fusion", "melting");
        correctQuestions += askQuestion("\nInformatics Question:", "What is the name of the inventor of modern computer architecture?", "johann von neumann", "von neumann");
        correctQuestions += askQuestion("\nSports Question:", "Which player has won the most number of ballon d'or?", "lionel messi", "messi");

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