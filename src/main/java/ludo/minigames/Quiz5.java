package ludo.minigames;
import java.util.Scanner;
public class Quiz5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");


        System.out.println("\nMath question:");
        System.out.println("What is the sum of the interior angles of a pentagon?");
        String answer1 = sc.nextLine();
        if (answer1.contains("540")) {
            correctQuestions++;
        }
        System.out.println("\nGeography Question:");
        System.out.println("Which important river flows through Cairo?");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("nile")) {
            correctQuestions++;
        }

        System.out.println("\nHistory Question:");
        System.out.println("Where was Napoleon exiled before dying?”");
        String answer3 = sc.nextLine();
        if (answer3.toLowerCase().contains("helena")||answer3.toLowerCase().contains("saint helena island")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("What is the chemical symbol for the element commonly known as 'table salt'?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("nacl") || answer4.toLowerCase().contains("sodium chloride")) {
            correctQuestions++;
        }
        System.out.println("\nInformatics Question:");
        System.out.println("What does the HTTP stand for in website URLs?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("hypertext transfer protocol")) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("Who holds the record for the fastest 100m sprint in the Olympics?");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("usain bolt")) {
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
