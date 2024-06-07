package ludo.minigames;

import java.util.Scanner;
public class Quiz6 {

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int correctQuestions = 0;
        System.out.println("General Knowledge MiniGame/n");
        System.out.println("Can you answer correctly to all the questions?/n");


        System.out.println("\nMath question:");
        System.out.println("What is the derivative of the function f(x) = 70.5x^2");
        String answer1 = sc.nextLine();
        if (answer1.toLowerCase().contains("141x")) {
            correctQuestions++;
        }

        System.out.println("\nGeography Question:");
        System.out.println("In which state is the northernmost McDonald's in the world located?");
        System.out.println("Hint: The state is in Europe");
        String answer2 = sc.nextLine();
        if (answer2.toLowerCase().contains("finland")) {
            correctQuestions++;
        }

        System.out.println("\nHistory Question:");
        System.out.println("In what year did the unification of Italy take place?");
        String answer3 = sc.nextLine();
        if (answer3.toLowerCase().contains("1861")) {
            correctQuestions++;
        }

        System.out.println("\nScience Question:");
        System.out.println("What is the transition from the solid to the liquid state called?");
        String answer4 = sc.nextLine();
        if (answer4.toLowerCase().contains("fusion")||answer4.toLowerCase().contains("melting")) {
            correctQuestions++;
        }

        System.out.println("\nInformatics Question:");
        System.out.println("What is the name of the inventor of modern computer architecture?");
        String answer5 = sc.nextLine();
        if (answer5.toLowerCase().contains("johann von neumann")|| answer5.toLowerCase().contains("von neumann")) {
            correctQuestions++;
        }

        System.out.println("\nSports Question:");
        System.out.println("Which player has won the most number of ballon d'or?");
        String answer6 = sc.nextLine();
        if (answer6.toLowerCase().contains("lionel messi") || answer6.toLowerCase().contains("messi")) {
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


