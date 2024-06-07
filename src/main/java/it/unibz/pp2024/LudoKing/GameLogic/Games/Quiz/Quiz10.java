package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import java.util.Scanner;

public class Quiz10 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame/n");
        System.out.println("Can you answer correctly to all the questions?/n");


        System.out.println("\nMath question:");
        System.out.println("What is the square root of 225?");
        String answer1 = sc.nextLine();
        if (answer1.contains("15")) {
            correctQuestions++;
        }

        System.out.println("\nGeography Question:");
        System.out.println("What is the largest island in the Mediterranean Sea?");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("sicily")) {
            correctQuestions++;
        }


        System.out.println("\nHistory Question:");
        System.out.println("In which year did the French Revolution start?");
        String answer3 = sc.nextLine();
        if (answer3.contains("1789")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("Which organs in the human body is primarily responsible for filtering blood?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("kidney") || answer4.toLowerCase().contains("kidneys")) {
            correctQuestions++;
        }

        System.out.println("\nInformatics Question:");
        System.out.println("How many bytes does a GigaByte contains?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("one billion") || answer5.toLowerCase().contains("1 billion")) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("Which sport uses terms like \"eagle,\" \"birdie,\" and \"bogey\"?");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("golf")) {
            correctQuestions++;
        }

        if (correctQuestions == 6) {
            System.out.println("\nCongrats, you won the mini-game!!!");
        }else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: "+correctQuestions+"/6");
        }
    }

}

