package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import java.util.Scanner;

public class Quiz8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame/n");
        System.out.println("Can you answer correctly to all the questions?/n");


        System.out.println("\nMath question:");
        System.out.println("What is the value of 5 factorial (5!)?");
        String answer1 = sc.nextLine();
        if (answer1.contains("120")) {
            correctQuestions++;
        }

        System.out.println("\nGeography Question:");
        System.out.println("Which continent is the least populated?");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("antartica")) {
            correctQuestions++;
        }

        System.out.println("\nHistory Question:");
        System.out.println("What was the number of the 'Apollo' mission, which was the first successful manned mission to the Moon?");
        String answer3 = sc.nextLine();
        if (answer3.contains("11") || answer3.toLowerCase().contains("eleven")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("Who is credited with formulating the laws of motion and universal gravitation?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("isaac newton") || answer4.toLowerCase().contains("newton")) {
            correctQuestions++;
        }

        System.out.println("\nInformatics Question:");
        System.out.println("In operating system, what does the acronym FIFO stands for?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("first in first out") || answer5.toLowerCase().contains("first in, first out") ) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("How many players are on a standard volleyball team on the court at one time?");
        String answer6 = sc.nextLine();
        if (answer6.contains("6")|| answer6.toLowerCase().contains("six")) {
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

