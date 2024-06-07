package ludo.minigames;
import java.util.Scanner;

public class Quiz1 {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame/n");
        System.out.println("Can you answer correctly to all the questions?/n");


        System.out.println("\nMath question:");
        System.out.println("Solve the equation: x^2 − 7x + 10 = 0");
        String answer1 = sc.nextLine();
        if (answer1.contains("5") && answer1.contains("2")) {
            correctQuestions++;
        }
        System.out.println("\nGeography Question:");
        System.out.println("What is the longest river in the world?");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("amazon river")) {
            correctQuestions++;
        }

        System.out.println("\nHistory Question:");
        System.out.println("Who was the first President of the United States?");
        String answer3 = sc.nextLine();
        if (answer3.toLowerCase().contains("george washington")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("What is the chemical symbol for gold?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("au")) {
            correctQuestions++;
        }

        System.out.println("\nInformatics Question:");
        System.out.println("What does HTML stand for?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("hypertext markup language")) {
            correctQuestions++;
        }
        // Sports
        System.out.println("\nSports Question:");
        System.out.println("In which sport do players try to knock down pins with a ball?");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("bowling")) {
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
