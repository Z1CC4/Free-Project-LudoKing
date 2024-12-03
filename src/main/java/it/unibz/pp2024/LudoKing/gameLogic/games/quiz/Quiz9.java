package it.unibz.pp2024.LudoKing.gameLogic.games.quiz;

import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Points;

import java.util.Scanner;


public class Quiz9 extends MiniGame {

    private final Scanner sc = new Scanner(System.in);
    public boolean play(Player p) {

        int correctQuestions=0;

        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        correctQuestions += askQuestion("\nMath question:", "If a train travels at a speed of 80 kilometers per hour for 2.5 hours, how far does it travel?", "200 kilometers", "200 km");
        correctQuestions += askQuestion("\nGeography Question:", "Which country is known as the 'Land of the Rising Sun'?", "japan");
        correctQuestions += askQuestion("\nHistory Question:", "What was the name of the ancient Greek city-state known for its military prowess and system of government?", "sparta");
        correctQuestions += askQuestion("\nScience Question:", "What is the name of the process by which plants convert sunlight into energy?", "photosynthesis", "chlorophyll photosynthesis", "photosyntesis", "photosintesis");
        correctQuestions += askQuestion("\nInformatics Question:", "What does the acronym USB stand for?", "universal serial bus");
        correctQuestions += askQuestion("\nSports Question:", "Who won the UEFA Euro 2020 (held in 2021)?", "italy");

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