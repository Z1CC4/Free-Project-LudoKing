package it.unibz.pp2024.LudoKing.application;

import it.unibz.pp2024.LudoKing.gameLogic.config.Game;
import it.unibz.pp2024.LudoKing.gameLogic.config.RandomGame;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Application {
    public static void main(String[] args){
        boolean validChoice=false;
        while(!validChoice){
            int choice=menu();
            System.out.println();
            switch(choice){
                case 1:


                    Game.ludoKing();
                    System.out.println("CASE 1 WAS CHOSEN");
                    validChoice=true;
                    break;
                case 2 :
                    RandomGame.startGame();
                    System.out.println("CASE 2 WAS CHOSEN");
                    validChoice=true;

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

}



