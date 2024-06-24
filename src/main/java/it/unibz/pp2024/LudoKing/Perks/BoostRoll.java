package it.unibz.pp2024.LudoKing.Perks;

import it.unibz.pp2024.LudoKing.Utils.Dice;
/*
this perk doubles the value of the roll
 */
public class BoostRoll {
    public static void main(String[] args) {
        BoostRoll boost = new BoostRoll();
        int roll = Dice.roll();
        int boostedRoll = boost.applyBoost(roll);
        System.out.println("Original roll: " + roll);
        System.out.println("Boosted roll: " + boostedRoll);
    }

    public int applyBoost(int roll) {
        return roll * 2;
    }

    public int rollAndBoost() {
        int roll = Dice.roll();
        return applyBoost(roll);
    }


}

