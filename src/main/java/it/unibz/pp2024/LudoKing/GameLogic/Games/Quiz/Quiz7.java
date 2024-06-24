package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;

public class Quiz7 {
    public static void returnPoints() {
        int points = 50;
        System.out.println("You obtained: " + points + " points.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame/n");
        System.out.println("Can you answer correctly to all the questions?/n");


        System.out.println("\nMath question:");
        System.out.println("What is the integral of cos(x)?");
        String answer1 = sc.nextLine();
        if (answer1.toLowerCase().contains("sinx") || answer1.toLowerCase().contains("sin(x)")) {
            correctQuestions++;
        }

        System.out.println("\nGeography Question:");
        System.out.println("In which country is the southernmost capital located?");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("new zealand")) {
            correctQuestions++;
        }

        System.out.println("\nHistory Question:");
        System.out.println("Who is the italian painter, who painted the last supper?");
        String answer3 = sc.nextLine();
        if (answer3.toLowerCase().contains("leonardo da vinci") || answer3.toLowerCase().contains("da vinci") || answer3.toLowerCase().contains("leonardo")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("What is the chemical symbol of Magnesium");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("mg")) {
            correctQuestions++;
        }

        System.out.println("\nInformatics Question:");
        System.out.println("Is Python a compiled or interpreted language?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("interpreted")) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("Which football team won the most number of UEFA Champions Leauge titles?");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("real madrid")) {
            correctQuestions++;
        }

        if (correctQuestions == 6) {
            System.out.println("\nCongrats, you won the mini-game!!!");
            returnPoints();
            hasPerkBoostRoll();
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
        }
    }

}

