package it.unibz.pp2024.LudoKing.Perks;

import it.unibz.pp2024.LudoKing.Utils.Dice;
import java.util.Scanner;

/*
this perk allows the player to roll the dice twice
and choose between one of the two rolls.
 */
public class DecideDoubleRoll {

    public static void main(String[] args) {
        DecideDoubleRoll decideDoubleRoll = new DecideDoubleRoll();
        decideDoubleRoll.rollDice();
        int chosenRoll = decideDoubleRoll.chooseRoll();
        System.out.println("You chose to use the roll: " + chosenRoll);
    }
    private int firstRoll;
    private int secondRoll;

    public void rollDice() {
        firstRoll = Dice.roll();
        secondRoll = Dice.roll();
    }

    public int chooseRoll() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You rolled a " + firstRoll + " and a " + secondRoll + ".");
        System.out.println("Which roll would you like to use? (Enter 1 for first roll or 2 for second roll):");

        int choice = scanner.nextInt();

        while (choice != 1 && choice != 2) {
            System.out.println("Invalid choice. Please enter 1 for first roll or 2 for second roll:");
            choice = scanner.nextInt();
        }

        return (choice == 1) ? firstRoll : secondRoll;
    }

}
