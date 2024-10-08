package it.unibz.pp2024.LudoKing.perks;

import it.unibz.pp2024.LudoKing.utils.Dice;

import java.util.Scanner;

/*
this perk allows the player to roll the dice twice
and choose between one of the two rolls.
 */
public class DecideDoubleRoll {

    public static int chooseRoll() {
        Scanner scanner = new Scanner(System.in);
        int firstRoll = Dice.roll();
        int secondRoll = Dice.roll();
        System.out.println("You rolled a " + firstRoll + " and a " + secondRoll + ".");
        System.out.println("Which roll would you like to use? (Enter 1 for first roll or 2 for second roll):");

        int choice = scanner.nextInt();

        while (choice != 1 && choice != 2) {
            System.out.println("Invalid choice. Please enter 1 for first roll or 2 for second roll:");
            choice = scanner.nextInt();
        }
        if (choice == 1) {
            System.out.println("You chose to use the roll: " + firstRoll);
        } else if (choice == 2) {
            System.out.println("You chose to use the roll: " + secondRoll);
        }

        return choice;
    }
}
