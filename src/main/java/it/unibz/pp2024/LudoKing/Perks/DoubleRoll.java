package it.unibz.pp2024.LudoKing.Perks;
import it.unibz.pp2024.LudoKing.Utils.Dice;
/*
this perk allows the player to roll
the dice twice and pick the highest one
 */
public class DoubleRoll {
    public static void main(String[] args) {
        DoubleRoll doubleRoll = new DoubleRoll();
        int result = doubleRoll.usePerk();
        System.out.println("Double Roll result: " + result);
    }
    public int usePerk() {
        int firstRoll = Dice.roll();
        int secondRoll = Dice.roll();
        System.out.println("The first roll is: "+firstRoll);
        System.out.println("The second roll is: "+secondRoll);
        return Math.max(firstRoll, secondRoll);
    }


}
