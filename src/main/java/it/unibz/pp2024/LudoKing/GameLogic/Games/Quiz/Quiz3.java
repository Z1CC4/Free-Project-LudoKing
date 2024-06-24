package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;

import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDoubleRoll;

public class Quiz3 {

    public static void returnPoints() {
        int points = 50;
        System.out.println("You obtained: " + points + " points.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");


        System.out.println("\nMath question:");
        System.out.println("What is the value of the derivative of the function f(x) = 3x^2 + 5x - 7 at x = 2?");
        String answer1 = sc.nextLine();
        if (answer1.contains("17")) {
            correctQuestions++;
        }
        System.out.println("\nGeography Question:");
        System.out.println("Which country has the largest land area in Africa?");
        System.out.println("Hint: It is on the Mediterran Sea");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("algeria")) {
            correctQuestions++;
        }

        System.out.println("\nHistory Question:");
        System.out.println("Who wrote the epic poem 'The Divine Comedy'?");
        String answer3 = sc.nextLine();
        if (answer3.toLowerCase().contains("dante")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("What is the chemical symbol for the element with the atomic number 20, and which is essential for bone health?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("ca") || answer4.toLowerCase().contains("calcium")) {
            correctQuestions++;
        }
        System.out.println("\nInformatics Question:");
        System.out.println("What data structure is used to implement a Last In, First Out (LIFO) system?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("stack")) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("Which country won the FIFA World Cup in 2018?");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("france")) {
            correctQuestions++;
        }

        if (correctQuestions == 6) {
            System.out.println("\nCongrats, you won the mini-game!!!");
            returnPoints();
            hasPerkDoubleRoll();
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
        }
    }

}
