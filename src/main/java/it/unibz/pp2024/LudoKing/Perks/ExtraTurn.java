package it.unibz.pp2024.LudoKing.Perks;
/*
this method allows the player
to play an extra turn
 */
public class ExtraTurn {
    public static void main(String[] args) {
        ExtraTurn extraTurn = new ExtraTurn();
        boolean extra = extraTurn.gainExtraTurn();
        System.out.println("Extra turn granted: " + extra);
    }
    public boolean gainExtraTurn() {
        return true;
    }
}
