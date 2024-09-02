package it.unibz.pp2024.LudoKing.perks;

import it.unibz.pp2024.LudoKing.utils.Dice;

public class DoubleRoll {

    public static int useDoubleRoll() {
        int firstRoll = Dice.roll();
        int secondRoll = Dice.roll();
        System.out.println("The first roll is: " + firstRoll);
        System.out.println("The second roll is: " + secondRoll);
        int maxRoll = Math.max(firstRoll, secondRoll);
        System.out.println("Double Roll result: " + maxRoll);
        return maxRoll;
    }
}
