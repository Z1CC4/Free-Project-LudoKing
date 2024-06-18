package it.unibz.pp2024.LudoKing.Perks;

import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.Utils.Color;
import it.unibz.pp2024.LudoKing.Utils.Dice;
import java.util.List;

/*
tiro il dado e avanzo del numero uscito, mentre gli avversari tornano indietro del numero uscito
 */
public class ReverseDice extends Player {

    public ReverseDice(String name, Color color) {
        super(name, color);
    }

    public static void main (String[] args){

    }
    public void PlayerAdvance(){
        int roll = Dice.roll();
        Player token;
            //int position = tokenToPosition.();
        if (isTurn){
            //position += roll;
        }
    }
}
