package it.unibz.pp2024.LudoKing.Utils;

/**
 * @author Francesco Masala (fmasala@unibz.it)
 * @version 1.0
 * A class that emulates a dice.
 */
public class Dice {
    public static int roll() {
        return (int) (Math.random() * 6) + 1;
    }

    public static int customRoll(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
