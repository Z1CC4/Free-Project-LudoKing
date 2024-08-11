package it.unibz.pp2024.LudoKing.Perks;

import it.unibz.pp2024.LudoKing.Utils.Dice;

public class BoostRoll {

    public static int rollAndBoost() {
        int roll = Dice.roll();
        int boostedRoll = roll * 2;
        System.out.println("Original roll: " + roll);
        System.out.println("Boosted roll: " + boostedRoll);
        return boostedRoll;  // Return the boosted roll
    }
}
