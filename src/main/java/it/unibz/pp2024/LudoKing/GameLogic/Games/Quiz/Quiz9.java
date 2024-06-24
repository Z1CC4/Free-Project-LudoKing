package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;

import java.util.Scanner;

public class Quiz9  {
    public static void returnPoints(){
        int points = 50;
        System.out.println("You obtained: "+ points + " points.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame/n");
        System.out.println("Can you answer correctly to all the questions?/n");


        System.out.println("\nMath question:");
        System.out.println("If a train travels at a speed of 80 kilometers per hour for 2.5 hours, how far does it travel?");
        String answer1 = sc.nextLine();
        if (answer1.contains("200 kilometers")|| answer1.contains("200 km")) {
            correctQuestions++;
        }

        System.out.println("\nGeography Question:");
        System.out.println("Which country is known as the 'Land of the Rising Sun'");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("japan")) {
            correctQuestions++;
        }


        System.out.println("\nHistory Question:");
        System.out.println("What was the name of the ancient Greek city-state known for its military prowess and system of government?");
        String answer3 = sc.nextLine();
        if (answer3.toLowerCase().contains("sparta")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("What is the name of the process by which plants convert sunlight into energy?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("photosynthesys") || answer4.toLowerCase().contains("chlorophyll photosynthesis") || answer4.toLowerCase().contains("photosyntesis") || answer4.toLowerCase().contains("photosintesis")) {
            correctQuestions++;
        }

        System.out.println("\nInformatics Question:");
        System.out.println("What does the acronym USB stands for?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("universal serial bus")) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("Who won the UEFA Euro 2020 (held in 2021)?");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("italy")) {
            correctQuestions++;
        }

        if (correctQuestions == 6) {
            System.out.println("\nCongrats, you won the mini-game!!!");
            returnPoints();
            hasPerkBoostRoll();
        }else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: "+correctQuestions+"/6");
        }
    }

}

