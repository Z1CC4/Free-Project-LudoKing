package it.unibz.pp2024.LudoKing.application;

import it.unibz.pp2024.LudoKing.gameLogic.config.Game;
import it.unibz.pp2024.LudoKing.gameLogic.config.RandomGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        boolean validChoice = false;
        while (!validChoice) {
            int choice = menu();
            System.out.println();
            switch (choice) {
                case 1:
                    Game.ludoKing();
                    System.out.println("CASE 1 WAS CHOSEN");
                    validChoice = true;
                    break;
                case 2:
                    // Ask for the number of players
                    int numPlayers = getNumberOfPlayers();
                    // Ask for the human player's index
                    int humanPlayerIndex = getHumanPlayerIndex(numPlayers);
                    // Ask for the human player's name
                    String humanPlayerName = getHumanPlayerName();

                    // Create an instance of RandomGame with the human player's name, the number of players, and the human player's index
                    RandomGame game = new RandomGame(humanPlayerName, numPlayers);
                    game.startGame();
                    System.out.println("CASE 2 WAS CHOSEN");
                    validChoice = true;
                    break;
                default:
                    System.out.println("Your choice is not valid.");
            }
        }
    }

    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (true) {
            try {
                System.out.println("Which version of the game do you want to play?");
                System.out.println("1. Four Players");
                System.out.println("2. Single Player");
                System.out.print("-->");
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        return choice;
    }

    // Ask for the number of players (1-4)
    private static int getNumberOfPlayers() {
        Scanner sc = new Scanner(System.in);
        int numPlayers = 0;
        while (numPlayers < 1 || numPlayers > 4) {
            System.out.print("Choose number of players (1-4): ");
            numPlayers = sc.nextInt();
            if (numPlayers < 1 || numPlayers > 4) {
                System.out.println("Invalid choice. Please select between 1 and 4 players.");
            }
        }
        return numPlayers;
    }

    // Ask for which player (1-4) is the human player
    private static int getHumanPlayerIndex(int numPlayers) {
        Scanner sc = new Scanner(System.in);
        int humanPlayerIndex = 0;
        while (humanPlayerIndex < 1 || humanPlayerIndex > numPlayers) {
            System.out.print("Choose which player (1-" + numPlayers + ") is the human player: ");
            humanPlayerIndex = sc.nextInt();
            if (humanPlayerIndex < 1 || humanPlayerIndex > numPlayers) {
                System.out.println("Invalid choice. Please select between 1 and " + numPlayers + ".");
            }
        }
        return humanPlayerIndex - 1;  // Convert to 0-based index
    }

    // Ask for the human player's name
    private static String getHumanPlayerName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Give a name for this player: ");
        return sc.nextLine();
    }
}
