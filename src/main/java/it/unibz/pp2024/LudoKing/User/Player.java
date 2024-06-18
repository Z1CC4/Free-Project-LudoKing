package it.unibz.pp2024.LudoKing.User;

import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.User.Points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.Scanner;

import it.unibz.pp2024.LudoKing.Utils.Color;
import it.unibz.pp2024.LudoKing.Utils.Dice;
import lombok.Getter;
import lombok.Setter;

public class Player {
    @Setter @Getter
    String name;
    List<Token> tokens;
    Map<Token, Integer> tokenToPosition;
    Color color;
    boolean hasFinished;//the winner is the one that finishes the first

    @Getter @Setter
    int inHome;//counts how many tokens are in the home
    Points points;

    boolean isTurn;
    boolean noTokenOut;
    //if no token has been pulled out, the player has to roll the dice until he gets 6



    public Player(String name, Color color){
        this.name=name;
        this.color=color;
        this.points=new Points();
        this.hasFinished=false;
        this.tokens=List.of(new Token(), new Token(), new Token(), new Token());
        this.tokenToPosition=new HashMap<>();
        this.inHome=inHome;
        this.isTurn=false;
        this.noTokenOut=false;
    }

    public void getPositionToken(Token token){
        try{
            for(Token t:tokenToPosition.keySet()){
                if(t.equals(token)){
                    System.out.println("The token is at the position"+tokenToPosition.get(token));
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("The token that you have provided does not exist");
        }
    }

    public void resetToken(){
        try{

        }catch(Exception e){

        }
    }

    public int chooseToken(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Choose the token that you want to move(insert the number)");
        for(Token t:tokenToPosition.keySet()){
            System.out.println(t.id);
        }
        return sc.nextInt();
    }
    public void moveToken(){
        int diceRoll= Dice.roll();
        int choice=chooseToken();


    }

    public void reset(Token token){
        try{
            token.position=0;
            tokenToPosition.put(token, 0);
        }catch(Exception e){
            System.out.println("This token does not exist");
        }

    }

    public void checkFinish(){
        if(inHome==4){
            hasFinished=true;
            System.out.println(name+" has finished.");
        }
    }

    public void startTurn(){
        this.isTurn=true;
        System.out.println(name+"'s turn has started.");
    }

    public void endTurn(){
        this.isTurn=false;
        System.out.println(name+"'s turn has ended.");
    }


}
