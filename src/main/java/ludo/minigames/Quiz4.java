package ludo.minigames;
import java.util.Scanner;

public class Quiz4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");


        System.out.println("\nMath question:");
        System.out.println("What is the sum of the first ten prime numbers?");
        String answer1 = sc.nextLine();
        if (answer1.contains("129")) {
            correctQuestions++;
        }
        System.out.println("\nGeography Question:");
        System.out.println("Which desert is the largest hot desert in the world?");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("sahara")) {
            correctQuestions++;
        }

        System.out.println("\nHistory Question:");
        System.out.println("In what year did Christopher Columbus first reach the Americas?");
        String answer3 = sc.nextLine();
        if (answer3.contains("1492")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("What is the most abundant gas in the Earth's atmosphere?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("nitrogen")) {
            correctQuestions++;
        }
        System.out.println("\nInformatics Question:");
        System.out.println("Which programming language, commonly used for data analysis and machine learning, was named after a television show in the 1970s?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("python")) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("Which tennis player holds the record for the most Grand Slam titles in men's singles as of 2023?");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("rafael nadal") || answer6.toLowerCase().contains("nadal")) {
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
