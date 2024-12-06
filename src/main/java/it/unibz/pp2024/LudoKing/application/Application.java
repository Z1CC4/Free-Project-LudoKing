package it.unibz.pp2024.LudoKing.application;

import it.unibz.pp2024.LudoKing.gameLogic.config.Game;
import it.unibz.pp2024.LudoKing.gameLogic.config.RandomGame;
import it.unibz.pp2024.LudoKing.user.Player;

public class Application {
    public static void main(String[] args){
        boolean validChoice=false;
        while(!validChoice){
            int choice=menu();
            System.out.println();
            switch(choice){
                case 1:
                    Game.ludoKing();
                    validChoice=true;
                    break;
                case 2:
                    RandomGame.startGame();
                    validChoice=true;
                    break;
                default:
                    System.out.println("Your choice is not valid.");
            }
        }

    }
    public static int menu(){
        System.out.println("Which version of the game do you want to play?");
        System.out.println("1.Four Players");
        System.out.println("2.Single Player");
        System.out.print("-->");
        return Player.checkValidInput();
    }
}



