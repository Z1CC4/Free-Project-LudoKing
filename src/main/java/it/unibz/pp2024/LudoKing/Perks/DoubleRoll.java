package it.unibz.pp2024.LudoKing.Perks;
import it.unibz.pp2024.LudoKing.Utils.Dice;
/*
this perk allows the player to roll
the dice twice and pick the highest one
 */
public class DoubleRoll {
    public static void main(String[] args) {
        useDoubleRoll();
    }
    public static void useDoubleRoll() {
        int firstRoll = Dice.roll();
        int secondRoll = Dice.roll();
        System.out.println("The first roll is: "+firstRoll);
        System.out.println("The second roll is: "+secondRoll);
        int maxRoll = Math.max(firstRoll, secondRoll);
        System.out.println("Double Roll result: " + maxRoll);

    }
}
