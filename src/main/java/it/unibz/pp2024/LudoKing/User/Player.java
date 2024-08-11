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
        this.roll = false;
        this.startingPos = new ArrayList<>(List.of(0, 16, 32, 48));
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

    public boolean getRoll() {
        return roll;
    }

    public void setRoll(boolean b) {
        this.roll = b;
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
        if (choice != -1) {
            tokenToPosition.entrySet().stream()
                    .filter(entry -> entry.getKey().getId() == choice)
                    .findFirst()
                    .ifPresentOrElse(
                            entry -> {
                                if (entry.getValue() != null) {
                                    System.out.println("The token is at the position " + entry.getValue());
                                } else {
                                    System.out.println("The token is not out yet.");
                                }

                            },
                            () -> System.out.println("The token that you have provided does not exist")
                    );
        }

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
        List<Token> tokensOut = new ArrayList<>();
        for (Token t : tokenToPosition.keySet()) {
            if (tokenToPosition.get(t) != null && !t.isHome()) {
                tokensOut.add(t);
            }
        }

        if (tokensOut.size() == 1) {
            return tokensOut.get(0).getId();
        } else if (tokensOut.size() <= 0) {
            System.out.println("There is no token out yet.");
            return -1;
        } else {
            System.out.println("Choose the token that you want to move(insert the number) bbbbb");
            for (Token t : tokensOut) {
                System.out.println("Token n." + t.getId());
            }
            System.out.print("-->");
            int choice = sc.nextInt();
            while (!isValidTokenChoice(choice)) {
                System.out.println("The number that you have inserted is not valid. Insert a valid one.");
                System.out.print("-->");
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
        List<Token> tokensOut = new ArrayList<>();
        if (choice > 5 || choice <= 0) {
            return false;
        }
        for (Token tok : tokenToPosition.keySet()) {
            if (tokenToPosition.get(tok) != null && !tok.isHome()) {
                tokensOut.add(tok);
            }
        }
        return tokensOut.stream().anyMatch(t -> t.getId() == choice && !t.isHome());
        //return tokenToPosition.keySet().stream().anyMatch(t -> t==choice && !t.isHome() && tokenToPosition.get(t).equals(null));
    }

    public boolean isValidTakeTokenOut(int choice){
        for(Token token:tokenToPosition.keySet()){
            if(token.getId()==choice && tokenToPosition.get(token)!=null){
                return false;
            }
        }
        return tokens.stream().anyMatch(t -> t.getId() == choice && !t.isHome());
    }


    public void takeTokenOut() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the token that you want to take out(insert the number) aaaaaaa");
        for (Token t : tokenToPosition.keySet()) {
            if (tokenToPosition.get(t) == null) {
                System.out.println("Token n." + t.getId());
            }
        }
        System.out.print("-->");
        int choice = sc.nextInt();
        while (!isValidTakeTokenOut(choice)) {
            System.out.println("The number that you have inserted is not valid. Insert a valid one.");
            System.out.print("-->");
            choice = sc.nextInt();
        }
        for(Token tt:tokenToPosition.keySet()){
            System.out.println("key:"+tt+" get:"+tokenToPosition.get(tt));
        }
        System.out.println("ziocaro"+tokenToPosition.get(choice));
        int finalChoice = choice;
        tokenToPosition.keySet().stream()
                .filter(t -> t.getId() == finalChoice)
                .findFirst()
                .ifPresent(t -> {
                    t.setPosition(0);
                    tokenToPosition.put(t, 0);;
                    Integer pos = startingPos.remove(0);
                    tokenToPositionOnMap.put(t, pos);
                    t.setStartingPos(pos);
                });

        /*for(Token token:tokenToPosition.keySet()){
            if(token.getId()==finalChoice){
                System.out.println("token:"+token.getId());
                token.setPosition(0);
                tokenToPosition.put(token, 0);;
                System.out.println("zio"+tokenToPosition.get(token));
                Integer pos = startingPos.remove(0);
                tokenToPositionOnMap.put(token, pos);
                token.setStartingPos(pos);
            }
        }*/



        System.out.println("Token " + choice + " has been taken out.");

    }


    public void moveToken() {
        setRoll(true);
        Scanner sc = new Scanner(System.in);
        List<Token> tokensOut = new ArrayList<>();
        for (Token tok : tokenToPosition.keySet()) {
            if (tokenToPosition.get(tok) != null && !tok.isHome()) {
                tokensOut.add(tok);
            }
        }

        while (getRoll()) {
            int diceRoll = Dice.roll();
            System.out.println("\"" + name + "\"" + " rolled a " + diceRoll);
            System.out.println();
            if (isNoTokenOut()) {
                if (diceRoll != 6) {
                    setRoll(false);
                } else {
                    takeTokenOut();
                    System.out.println();
                    setNoTokenOut(false);
                    //setRoll(false);
                }
            } else {
                if (diceRoll == 6) {
                    boolean validMove = false;
                    if(!isValidMove(diceRoll) && tokenToPosition.keySet().stream().allMatch(token->tokenToPosition.get(token)!=null)){
                        System.out.println("No move allowed in this turn.");
                        setRoll(false);
                        validMove=true;
                    }
                    while (!validMove) {
                        displayChoices();
                        boolean validChoice = false;
                        while (!validChoice) {
                            switch (sc.nextInt()) {
                                case 1:
                                    if (isValidMove(diceRoll)) {
                                        System.out.print("-->");
                                        int choice = chooseToken();
                                        while (!isValidTokenChoice(choice)) {
                                            System.out.println("The number that you have inserted is not valid. Insert a valid one.");
                                            System.out.print("-->");
                                            choice = sc.nextInt();
                                        }
                                        updateTokenPosition(choice, diceRoll, sc);
                                        checkIsHome(choice);
                                        //setRoll(false);
                                        validChoice = true;
                                        validMove = true;
                                    } else {
                                        System.out.println();
                                        System.out.println("No token can be moved");
                                        System.out.println();
                                        displayChoices();
                                    }
                                    break;
                                case 2:
                                    //if (tokensOut.size() == 4 ) {
                                    if (tokenToPosition.keySet().stream().allMatch(token->tokenToPosition.get(token)!=null)) {
                                        System.out.println("All tokens are already out. Make another choice.");
                                        System.out.println();
                                        displayChoices();
                                    } else {
                                        takeTokenOut();
                                        validChoice = true;
                                        validMove = true;
                                        //setRoll(false);

                                    }
                                    break;
                                default:
                                    System.out.println("Insert a valid number.");
                            }
                        }
                    }
                } else {
                    if(isValidMove(diceRoll)){
                        int choice = chooseToken();
                        while (!isValidTokenChoice(choice)) {
                            System.out.println("The number that you have inserted is not valid. Insert a valid one.");
                            System.out.print("-->");
                            choice = sc.nextInt();
                        }
                        updateTokenPosition(choice, diceRoll, sc);
                        checkIsHome(choice);
                        setRoll(false);
                    }else{
                        System.out.println("No move allowed in this turn.");
                        setRoll(false);
                    }

                }
            }
        }

    }

    public void displayChoices() {
        System.out.println("Do you want to either move a token or to take out one? (Insert the number)");
        System.out.println("1. Move a token.");
        System.out.println("2. Take out a token.");
    }

    public boolean isValidMove(int diceRoll) {
        List<Token> tokensOut = new ArrayList<>();
        for (Token tok : tokenToPosition.keySet()) {
            if (tokenToPosition.get(tok) != null && !tok.isHome()) {
                tokensOut.add(tok);
            }
        }
        return tokensOut.stream()
                .anyMatch(token ->
                        tokenToPosition.get(token) + diceRoll <= Game.getCells() - 1
                );
    }


    public boolean isAnyTokenPositionNull() {
        return tokenToPosition.keySet().stream()
                .anyMatch(token -> token.getPosition() == null);
    }

    public void updateTokenPosition(int toUpdate, int rollResult, Scanner sc) {
        List<Token> tokensOut = new ArrayList<>();
        for (Token tok : tokenToPosition.keySet()) {
            if (tokenToPosition.get(tok) != null && !tok.isHome()) {
                tokensOut.add(tok);
            }
        }
        for (Token t : tokenToPosition.keySet()) {
            if (t.getId() == toUpdate) {
                if (Game.getCells() - 1 - tokenToPosition.get(t) <= 6) { //if the player is within the last 6 tiles
                    if (tokenToPosition.get(t) + rollResult == Game.getCells() - 1) {
                        tokenToPosition.put(t, t.getPosition() + rollResult);
                        tokenToPositionOnMap.put(t, tokenToPositionOnMap.get(t) + rollResult);
                        if (tokenToPositionOnMap.get(t) > Game.getCells() - 1) {
                            int temp = tokenToPositionOnMap.get(t) - (Game.getCells() - 1);
                            tokenToPositionOnMap.put(t, temp);
                        }
                        t.setPosition(t.getPosition() + rollResult);
                        System.out.println(t.getPosition());
                        t.setHome(true);
                        System.out.println("Token n." + t.getId() + " is in the home!");
                        tokensOut.remove(t);
                        if (tokensOut.isEmpty()) {
                            setNoTokenOut(true);
                        }
                    } else if (tokenToPosition.get(t) + rollResult <= Game.getCells() - 1) {
                        tokenToPosition.put(t, t.getPosition() + rollResult);
                        tokenToPositionOnMap.put(t, tokenToPositionOnMap.get(t) + rollResult);
                        if (tokenToPositionOnMap.get(t) > Game.getCells() - 1) {
                            int temp = tokenToPositionOnMap.get(t) - (Game.getCells() - 1);
                            tokenToPositionOnMap.put(t, temp);
                        }
                        t.setPosition(t.getPosition() + rollResult);
                    } else {
                        if (tokensOut.size() > 1 && !t.isHome()) {
                            System.out.println("You need to roll exactly " + (Game.getCells() - 1 - tokenToPosition.get(t)) + " to move the token to home. Make a valid choice.");
                            System.out.print("-->");
                            int newToUpdate = sc.nextInt();
                            updateTokenPosition(newToUpdate, rollResult, sc);
                        } else if (!tokensOut.contains(t) && tokenToPosition.get(t)==null) {
                            System.out.println("This token is not out. Select a valid one.");
                        } else if(t.getPosition()==Game.getCells()-1){
                            System.out.println("This token is already in the base.");
                        }else {
                            System.out.println("You need to roll exactly " + (Game.getCells() - 1 - tokenToPosition.get(t)) + " to move the token to home.");
                        }

                    }
                } else {
                    tokenToPosition.put(t, t.getPosition() + rollResult);
                    tokenToPositionOnMap.put(t, tokenToPositionOnMap.get(t) + rollResult);
                    if (tokenToPositionOnMap.get(t) > Game.getCells() - 1) {
                        int temp = tokenToPositionOnMap.get(t) - (Game.getCells() - 1);
                        tokenToPositionOnMap.put(t, temp);
                    }
                    t.setPosition(t.getPosition() + rollResult);
                    System.out.println(t.getPosition());
                }
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

    //These four methods check if the player wins the game as first, second, third or fourth (hope it works).
    public boolean checkFinishFirst() {
        if (inHome == 4) {
            hasFinished = true;
            Points.addPoints(150);
            System.out.println(name + " has finished.");
            return true;
        }
        return false;
    }

    public boolean checkFinishSecond() {
        if (checkFinishFirst()) {
            if (inHome == 4) {
                hasFinished = true;
                Points.addPoints(125);
                System.out.println(name + " has finished.");
                return true;
            }
        }
        return false;
    }

    public boolean checkFinishThird() {
        if (checkFinishFirst()) {
            if (checkFinishSecond()) {
                if (inHome == 4) {
                    hasFinished = true;
                    Points.addPoints(110);
                    System.out.println(name + " has finished.");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkFinishFourth() {
        if (checkFinishFirst()) {
            if (checkFinishSecond()) {
                if (checkFinishThird()) {
                    if (inHome == 4) {
                        hasFinished = true;
                        Points.addPoints(90);
                        System.out.println(name + " has finished.");
                        return true;
                    }
                }
            }
        }
        return false;
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