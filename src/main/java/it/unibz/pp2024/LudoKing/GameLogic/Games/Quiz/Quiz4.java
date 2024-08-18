package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import it.unibz.pp2024.LudoKing.User.Player;

import java.util.Scanner;


public class Quiz4 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    //private int correctQuestions = 0;
    //private final Player playerObj;

    /*public Quiz4(Player player) {
        this.playerObj = player;
    }*/
    @Override
    public boolean play(Player p) {

        int correctQuestions=0;

        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "What is the value of the derivative of the function f(x) = 3x^2 + 5x - 7 at x = 2?", "17");
        correctQuestions += askQuestion("\nGeography Question:", "Which country has the largest land area in Africa? (Hint: It is on the Mediterranean Sea)", "algeria");
        correctQuestions += askQuestion("\nHistory Question:", "Who wrote the epic poem 'The Divine Comedy'?", "dante");
        correctQuestions += askQuestion("\nScience Question:", "What is the chemical symbol for the element with the atomic number 20, essential for bone health?", "ca", "calcium");
        correctQuestions += askQuestion("\nInformatics Question:", "What data structure is used to implement a Last In, First Out (LIFO) system?", "stack");
        correctQuestions += askQuestion("\nSports Question:", "Which country won the FIFA World Cup in 2018?", "france");

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
