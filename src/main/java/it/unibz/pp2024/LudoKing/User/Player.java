package it.unibz.pp2024.LudoKing.User;

import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.Perks.BoostRoll;
import it.unibz.pp2024.LudoKing.Perks.DecideDoubleRoll;
import it.unibz.pp2024.LudoKing.Perks.DoubleRoll;
import it.unibz.pp2024.LudoKing.Utils.Color;
import it.unibz.pp2024.LudoKing.Utils.Dice;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDoubleRoll;
import static it.unibz.pp2024.LudoKing.MiniGames.GuessTheWord.hasPerkDecideDoubleRoll;

public class Player<P> {
    @Setter
    @Getter
    private String name;
    private List<Token> tokens;
    private Map<Token, Integer> tokenToPosition;
    private Color color;
    private boolean hasFinished;
    @Getter
    @Setter
    private int inHome;//counts how many tokens are in the home
    private Points points;

    private boolean isTurn;
    private boolean noTokenOut;
    //if no token has been pulled out, the player has to roll the dice until he gets 6

    public Player(String name, Color color, int inHome) {
        this.name = name;
        this.color = color;
        //this.points=new Points(0);
        this.hasFinished = false;
        this.tokens = List.of(new Token(1, 0), new Token(2, 0), new Token(3, 0), new Token(4, 0));
        this.tokenToPosition = new HashMap<>();
        for (Token t : tokens) {
            tokenToPosition.put(t, t.position);
        }
        this.inHome = inHome;
        this.isTurn = false;
        this.noTokenOut = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Map<Token, Integer> getTokenToPosition() {
        return tokenToPosition;
    }

    public void setTokenToPosition(Map<Token, Integer> tokenToPosition) {
        this.tokenToPosition = tokenToPosition;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public int getInHome() {
        return inHome;
    }

    public void setInHome(int inHome) {
        this.inHome = inHome;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public boolean getIsTurn() {
        return isTurn;
    }

    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public boolean isNoTokenOut() {
        return noTokenOut;
    }

    public void setNoTokenOut(boolean noTokenOut) {
        this.noTokenOut = noTokenOut;
    }

    public void getPositionToken() {
        /*try{
            for(Token t:tokenToPosition.keySet()){
                if(t.equals(token)){
                    System.out.println("The token is at the position"+tokenToPosition.get(token));
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("The token that you have provided does not exist");*/


        int choice = chooseToken();
            /*
            for(Token t:tokenToPosition.keySet()){
                if(t.id==choice){
                    System.out.println("The token is at the position "+tokenToPosition.get(t));
                }
            }*/

        tokenToPosition.entrySet().stream()
                .filter(entry -> entry.getKey().id == choice)
                .findFirst()
                .ifPresentOrElse(
                        entry -> System.out.println("The token is at the position " + entry.getValue()),
                        () -> System.out.println("The token that you have provided does not exist")
                );
    }

    public void setTokenColorsToPlayerColor() {
        tokenToPosition.keySet().forEach(token -> token.setColor(color));
    }

   /* public void resetToken(){
        try{

        }catch(Exception e){

        }
    }*/

    public void usePerkBoostRoll() {
        if (hasPerkBoostRoll()) {
            BoostRoll.rollAndBoost();
        }
    }

    public void usePerkDecideDoubleRoll() {
        if (hasPerkDecideDoubleRoll()) {
            DecideDoubleRoll.chooseRoll();
        }
    }

    public void useDoubleRoll() {
        if (hasPerkDoubleRoll()) {
            DoubleRoll.useDoubleRoll();
        }
    }

    public int chooseToken() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the token that you want to move(insert the number)");
        for (Token t : tokenToPosition.keySet()) {
            System.out.println(t.id);
        }
       /* boolean isValid=false;
        while(isValid==false){
            int choice=sc.nextInt();
            for(Token t:tokens){
                if(choice==t.id){
                    isValid=true;
                }else{
                    System.out.println("The number that you have inserted is not valid. Insert a valid one.");
                }
            }
        }
        return sc.nextInt();*/
        int choice = sc.nextInt();
        while (!isValidTokenChoice(choice)) {
            System.out.println("The number that you have inserted is not valid. Insert a valid one.");
            choice = sc.nextInt();
        }
        return choice;
    }

    private boolean isValidTokenChoice(int choice) {
        return tokens.stream().anyMatch(t -> t.id == choice);
    }

    public void moveToken() {
        int diceRoll = Dice.roll();
        System.out.println(name + " rolled a " + diceRoll);
        while (diceRoll == 6) {
            int choice = chooseToken();
            updateTokenPosition(choice, diceRoll);
            checkIsHome(choice);
        }
    }

    public void updateTokenPosition(int toUpdate, int rollResult) {
        for (Token t : tokenToPosition.keySet()) {
            if (t.getId() == toUpdate) {
                tokenToPosition.put(t, t.position + rollResult);
                //t.position+=rollResult;
                t.setPosition(t.getPosition() + rollResult);
            }
        }
    }

    public void checkIsHome(int idTok) {
        for (Token t : tokens) {
            if (t.id == idTok) {
                if (t.isHome) {
                    inHome++;
                    //addPoints(50);
                }
            }
        }
    }


    public void reset(Token token) {
        try {
            token.position = 0;
            tokenToPosition.put(token, 0);
        } catch (Exception e) {
            System.out.println("This token does not exist");
        }

    }

    public void checkFinish() {
        if (inHome == 4) {
            hasFinished = true;
            //addPoints(50);
            System.out.println(name + " has finished.");
        }
    }

    public void startTurn() {
        //this.isTurn=true;
        setIsTurn(true);
        System.out.println(name + "'s turn has started.");
    }

    public void endTurn() {
        //this.isTurn=false;
        setIsTurn(false);
        System.out.println(name + "'s turn has ended.");
    }


}
