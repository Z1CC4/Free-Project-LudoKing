package ludo.minigames;
import java.util.Scanner;

public class Quiz2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");


        System.out.println("\nMath question:");
        System.out.println("What is the value of the square root of 169?");
        String answer1 = sc.nextLine();
        if (answer1.contains("13")) {
            correctQuestions++;
        }
        System.out.println("\nGeography Question:");
        System.out.println("What is the capital city of Australia?");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("canberra")) {
            correctQuestions++;
        }

        System.out.println("\nHistory Question:");
        System.out.println("In which year did World War II end?");
        String answer3 = sc.nextLine();
        if (answer3.contains("1945")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("Which planet is known as the Red Planet?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("mars")) {
            correctQuestions++;
        }
        System.out.println("\nInformatics Question:");
        System.out.println("What is the most commonly used programming language for web development?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("javascript")) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("Who holds the record for the most Olympic gold medals?");
        System.out.println("Hint: Swimmer");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("michael phelps")) {
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

