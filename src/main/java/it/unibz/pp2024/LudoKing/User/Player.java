package it.unibz.pp2024.LudoKing.User;
import it.unibz.pp2024.LudoKing.User.Points;
import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.Perks.BoostRoll;
import it.unibz.pp2024.LudoKing.Perks.DecideDoubleRoll;
import it.unibz.pp2024.LudoKing.Perks.DoubleRoll;
import it.unibz.pp2024.LudoKing.Utils.Color;
import it.unibz.pp2024.LudoKing.Utils.Dice;
import it.unibz.pp2024.LudoKing.GameLogic.Config.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.*;


public class Player<P> {
    @Setter
    @Getter
    private String name;
    private List<Token> tokens;
    private Map<Token, Integer> tokenToPosition; //mapping tokens to their positions(0-63)

    private Map<Token, Integer> tokenToPositionOnMap;
    /* mapping tokens to their positions on the map. The difference with the other map is that
    since we cannot make all tokens start from the same position, we are going to make them start to distanciated points.
    */
    private Color color;
    private boolean hasFinished;
    @Getter
    @Setter
    private int inHome;//counts how many tokens are in the home
    private Points points;

    private boolean isTurn;
    private boolean noTokenOut;
    //if no token has been pulled out, the player has to roll the dice until he gets 6

    private boolean roll;

    List<Integer> startingPos;


    public Player(String name, Color color, int inHome) {
        this.name = name;
        this.color = color;
        //this.points=new Points(0);
        this.hasFinished = false;
        //this.tokens = List.of(new Token(1, null, null), new Token(2, null, null), new Token(3, null, null), new Token(4, null, null));
        this.tokens = List.of(new Token(1, null), new Token(2, null), new Token(3, null), new Token(4, null));

        this.tokenToPosition = new HashMap<>();
        for (Token t : tokens) {
            tokenToPosition.put(t, t.getPosition());
        }
        this.tokenToPositionOnMap = new HashMap<>();
        for (Token t : tokens) {
            tokenToPositionOnMap.put(t, null);
        }
        this.inHome = inHome;
        this.isTurn = false;
        this.noTokenOut = true;
        this.roll=false;
        this.startingPos=new ArrayList<>(List.of(0,16,32,48));
        Collections.shuffle(startingPos);
    }

    public void setStartingPositions(Token t, Integer pos) {
        //for (Token t : tokens) {
            tokenToPositionOnMap.put(t, pos);
        //}
    }

    public void updateTokenPositionOnMap(Token token, int newPosition) {
        tokenToPositionOnMap.put(token, newPosition);
    }

    public boolean getRoll(){
        return roll;
    }

    public void setRoll(boolean b){
        this.roll=b;
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

    public void setTokenToPositionOnMap(Map<Token, Integer> tokenToPositionOnMap) {
        this.tokenToPositionOnMap = tokenToPositionOnMap;
    }

    public Map<Token, Integer> getTokenToPositionOnMap() {
        return tokenToPositionOnMap;
    }

    /*public void addEntriesTokToPosMap(Token t, Integer posOnMap){
        t.setPositionOnMap(t.getPosition()+posOnMap);
        tokenToPositionOnMap.put(t, posOnMap);
    }*/

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
                .filter(entry -> entry.getKey().getId() == choice)
                .findFirst()
                .ifPresentOrElse(
                        entry -> {
                            if(entry.getValue()!=null){
                                System.out.println("The token is at the position " + entry.getValue());
                            }else{
                                System.out.println("The token is not out yet.");
                            }

                        },
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

    public boolean useBoostRoll() {
        return hasPerkBoostRoll();
    }

    public boolean useDoubleRoll() {
        return hasPerkDoubleRoll();
    }

    public boolean useDecideDoubleRoll() {
        return hasPerkDecideDoubleRoll();
    }


    public int chooseToken() {
        Scanner sc = new Scanner(System.in);
        List<Token> tokensOut=new ArrayList<>();
        for (Token t : tokenToPosition.keySet()) {
            if(tokenToPosition.get(t)!=null && !t.isHome()){
                tokensOut.add(t);
            }
        }

        if(tokensOut.size()==1){
            return tokensOut.get(0).getId();
        }else{
            System.out.println("Choose the token that you want to move(insert the number)");
            for(Token t:tokensOut){
                System.out.println(t.getId());
            }
            int choice = sc.nextInt();
            while (!isValidTokenChoice(choice)) {
                System.out.println("The number that you have inserted is not valid. Insert a valid one.");
                choice = sc.nextInt();
            }
            return choice;
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

    }

    private boolean isValidTokenChoice(int choice) {
        return tokens.stream().anyMatch(t -> t.getId() == choice && !t.isHome());
    }

    public void takeTokenOut(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the token that you want to take out(insert the number)");
        for (Token t : tokenToPosition.keySet()) {
                if(tokenToPosition.get(t)==null){
                    System.out.println(t.getId());
                }
        }
        int choice = sc.nextInt();
        while (!isValidTokenChoice(choice)) {
            System.out.println("The number that you have inserted is not valid. Insert a valid one.");
            choice = sc.nextInt();
        }

        int finalChoice = choice;
        tokenToPosition.keySet().stream()
                .filter(t -> t.getId() == finalChoice)
                .findFirst()
                .ifPresent(t -> {
                    t.setPosition(0);
                    tokenToPosition.put(t, 0);
                    Integer pos=startingPos.remove(0);
                    tokenToPositionOnMap.put(t, pos);
                    t.setStartingPos(pos);
                });


        System.out.println("Token " + choice + " has been taken out.");

    }


    public void moveToken() {
        Scanner sc = new Scanner(System.in);
        setRoll(true);

        while (getRoll()) {
            int diceRoll = 0;

            if (hasPerkDoubleRoll()) {
                System.out.println("Using the 'Double Roll' perk...");
                diceRoll = DoubleRoll.useDoubleRoll();
                setPerkDoubleRoll(false);
                setRoll(false);
            } else if (hasPerkBoostRoll()) {
                System.out.println("Using the 'Boost Roll' perk...");
                diceRoll = BoostRoll.rollAndBoost();  // Use the returned boosted roll
                setPerkBoostRoll(false);
                setRoll(false);
            } else {
                diceRoll = Dice.roll();
                System.out.println(name + " rolled a " + diceRoll);
            }

            if (isNoTokenOut()) {
                if (diceRoll != 6) {
                    setRoll(false); // End turn if not a 6
                } else {
                    takeTokenOut(); // Take out a token on rolling a 6
                    setNoTokenOut(false);
                    // No break here as the player gets another roll
                }
            } else {
                if (diceRoll == 6) {
                    System.out.println("Do you want to either move a token or to take out one? (Insert the number)");
                    System.out.println("1. Move a token.");
                    System.out.println("2. Take out a token.");
                    boolean valid = false;
                    while (!valid) {
                        int action = sc.nextInt();
                        if (action == 1) {
                            int choice = chooseToken();
                            updateTokenPosition(choice, diceRoll);
                            checkIsHome(choice);
                            valid = true;
                            // Continue the loop because the player gets another roll after moving
                        } else if (action == 2) {
                            takeTokenOut();
                            valid = true;
                            // Continue the loop because the player gets another roll after taking out a token
                        } else {
                            System.out.println("Insert a valid number.");
                        }
                    }
                } else {
                    int choice = chooseToken();
                    updateTokenPosition(choice, diceRoll);
                    checkIsHome(choice);
                    setRoll(false); // End turn if the roll wasn't a 6
                }
            }
        }
    }



    public boolean isAnyTokenPositionNull() {
        return tokenToPosition.keySet().stream()
                .anyMatch(token -> token.getPosition() == null);
    }

    public void updateTokenPosition(int toUpdate, int rollResult) {
        for (Token t : tokenToPosition.keySet()) {
            if (t.getId() == toUpdate) {
                tokenToPosition.put(t, t.getPosition() + rollResult);
                tokenToPositionOnMap.put(t, tokenToPositionOnMap.get(t)+rollResult);
                if(tokenToPositionOnMap.get(t)>Game.getCells()-1){
                    int temp=tokenToPositionOnMap.get(t)-(Game.getCells()-1);
                    tokenToPositionOnMap.put(t,temp);
                }
                //t.position+=rollResult;
                t.setPosition(t.getPosition() + rollResult);
                System.out.println(t.getPosition());
                if(t.getPosition()==(Game.getCells()-1)){
                    t.setHome(true);
                }
              //  checkFinish();
            }
        }
    }

    public void checkIsHome(int idTok) {
        for (Token t : tokens) {
            if (t.getId() == idTok) {
                if (t.isHome()) {
                    inHome++;
                }
            }
        }
    }


    public void reset(Token token) {
        try {
            token.setPosition(null);
            tokenToPosition.put(token, null);
            tokenToPositionOnMap.put(token, null);
            token.setStartingPos(null);
        } catch (Exception e) {
            System.out.println("This token does not exist");
        }

    }

public boolean checkFinish(int order) {
    if (inHome == 4) {
        hasFinished = true;
        int pointsAwarded = 0;
        switch (order) {
            case 1:
                pointsAwarded = 150;
                break;
            case 2:
                pointsAwarded = 125;
                break;
            case 3:
                pointsAwarded = 110;
                break;
            case 4:
                pointsAwarded = 90;
                break;
            default:
                break;
        }
        Points.addPoints(pointsAwarded);
        System.out.println(name + " has finished " + getOrdinal(order) + " and earned " + pointsAwarded + " points.");
        return true;
    }
    return false;
}

    private String getOrdinal(int order) {
        switch (order) {
            case 1:
                return "first";
            case 2:
                return "second";
            case 3:
                return "third";
            case 4:
                return "fourth";
            default:
                return "";
        }
    }
    public void startTurn(){
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
