package it.unibz.pp2024.LudoKing.Perks;

import it.unibz.pp2024.LudoKing.Utils.Dice;

/*
this perk doubles the value of the roll
 */
public class BoostRoll {

    public static void rollAndBoost() {
        int roll = Dice.roll();
        int boostedRoll = roll * 2;
        System.out.println("Original roll: " + roll);
        System.out.println("Boosted roll: " + boostedRoll);
    }
}

