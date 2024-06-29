package it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz;
import java.util.Scanner;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;

public class Quiz6 extends MiniGame {

    private Scanner sc = new Scanner(System.in);
    private int correctQuestions = 0;

    public void play() {
        System.out.println("General Knowledge MiniGame");
        System.out.println("Can you answer correctly to all the questions?");

        if (askQuestion1()) correctQuestions++;
        if (askQuestion2()) correctQuestions++;
        if (askQuestion3()) correctQuestions++;
        if (askQuestion4()) correctQuestions++;
        if (askQuestion5()) correctQuestions++;
        if (askQuestion6()) correctQuestions++;

        if (correctQuestions == 6) {
            System.out.println("\nCongrats, you won the mini-game!!!");
            QuizReturnPoints.returnPoints(50);
            hasPerkBoostRoll();
        } else {
            System.out.println("\nYou lost the mini-game");
            System.out.println("Your correct answers: " + correctQuestions + "/6");
        }
    }

    private boolean askQuestion1() {
        System.out.println("\nMath question:");
        System.out.println("What is the derivative of the function f(x) = 70.5x^2?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("141x");
    }

    private boolean askQuestion2() {
        System.out.println("\nGeography Question:");
        System.out.println("In which state is the northernmost McDonald's in the world located?");
        System.out.println("Hint: The state is in Europe");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("finland");
    }

    private boolean askQuestion3() {
        System.out.println("\nHistory Question:");
        System.out.println("In what year did the unification of Italy take place?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("1861");
    }

    private boolean askQuestion4() {
        System.out.println("\nScience Question:");
        System.out.println("What is the transition from the solid to the liquid state called?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("fusion") || answer.toLowerCase().contains("melting");
    }
  
    private boolean askQuestion5() {
        System.out.println("\nInformatics Question:");
        System.out.println("What is the name of the inventor of modern computer architecture?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("johann von neumann") || answer.toLowerCase().contains("von neumann");
    }

    private boolean askQuestion6() {
        System.out.println("\nSports Question:");
        System.out.println("Which player has won the most number of ballon d'or?");
        String answer = sc.nextLine();
        return answer.toLowerCase().contains("lionel messi") || answer.toLowerCase().contains("messi");
    }
}
