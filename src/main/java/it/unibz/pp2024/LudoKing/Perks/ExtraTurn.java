package it.unibz.pp2024.LudoKing.Perks;

import it.unibz.pp2024.LudoKing.Utils.Dice;

/*
this method allows the player
to play an extra turn
 */
public class ExtraTurn {
    public static int gainExtraTurn() {
        int roll = Dice.roll();
        System.out.println("\nYou obtained: 'Extra Turn' perk");
        System.out.println("Your extra roll is: " + Dice.roll());
        return roll;
    }
}
