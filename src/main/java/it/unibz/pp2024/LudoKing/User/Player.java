package it.unibz.pp2024.LudoKing.User;

import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.User.Points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


import it.unibz.pp2024.LudoKing.Utils.Color;
import lombok.Getter;
import lombok.Setter;

public class Player {
    @Setter @Getter
    String name;
    public List<Token> tokens;
    public Map<Token, Integer> tokenToPosition;
    Color color;
    boolean isWinner;//the winner is the one that finishes the first

    @Getter @Setter
    int inHome;//counts how many tokens are in the home
    Points points;

    public boolean isTurn;

    public Player(String name, Color color){
        this.name=name;
        this.color=color;
        this.points=new Points();
        this.isWinner=false;
        this.tokens=List.of(new Token(), new Token(), new Token(), new Token());
        this.tokenToPosition=new HashMap<>();
        this.inHome=inHome;
        this.isTurn=false;
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

    public void reset(Token token){

    }

    public void checkWin(){
        if(inHome==4){
            isWinner=true;
            System.out.println(name+" has won the game");
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
