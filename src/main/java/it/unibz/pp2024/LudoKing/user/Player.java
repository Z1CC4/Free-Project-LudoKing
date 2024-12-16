package it.unibz.pp2024.LudoKing.user;

import it.unibz.pp2024.LudoKing.gameLogic.config.Game;
import it.unibz.pp2024.LudoKing.perks.BoostRoll;
import it.unibz.pp2024.LudoKing.perks.DecideDoubleRoll;
import it.unibz.pp2024.LudoKing.perks.DoubleRoll;
import it.unibz.pp2024.LudoKing.utils.Color;
import it.unibz.pp2024.LudoKing.utils.Dice;
import it.unibz.pp2024.LudoKing.utils.Points;
import it.unibz.pp2024.LudoKing.utils.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Player {

    @Setter
    @Getter
    private String name;
    @Getter
    private final List<Token> tokens;
    private final List<Token> tokensOut;
    private final Map<Token, Integer> tokenToPosition;

    private final Map<Token, Integer> tokenToPositionOnMap;

    private final Color color;
    private boolean hasFinished;
    @Getter
    @Setter
    private int inHome;
    private boolean isAI;
    private Points points;

    private boolean isTurn;
    @Getter
    @Setter
    private boolean noTokenOut;


    @Setter
    private boolean roll;
    List<Integer> startingPos;
    @Setter
    private boolean perkDoubleRoll = false;
    @Setter
    private boolean perkBoostRoll = false;
    @Setter
    private boolean perkDecideDoubleRoll = false;

    final int base1 = 0;
    final int base2 = 16;
    final int base3 = 32;
    final int base4 = 48;

    public Player(String name, Color color, int inHome) {
        this.name = name;
        this.color = color;
        this.isAI = isAI;
        this.points = new Points();
        this.hasFinished = false;
        this.tokens = List.of(new Token(1, null, null), new Token(2, null, null), new Token(3, null, null), new Token(4, null, null));

        this.tokenToPosition = new HashMap<>();
        for (Token t : tokens) {
            tokenToPosition.put(t, t.getPosition());
        }
        this.tokenToPositionOnMap = new HashMap<>();
        for (Token t : tokens) {
            tokenToPositionOnMap.put(t, null);
        }
        this.tokensOut = new ArrayList<>();
        this.inHome = inHome;
        this.isTurn = false;
        this.noTokenOut = true;
        this.roll = false;
        this.startingPos = new ArrayList<>(List.of(base1, base2, base3, base4));
        Collections.shuffle(startingPos);


    }

    public boolean hasPerkDoubleRoll() {
        return perkDoubleRoll;
    }

    public boolean hasPerkBoostRoll() {
        return perkBoostRoll;
    }

    public boolean hasPerkDecideDoubleRoll() {
        return perkDecideDoubleRoll;
    }

    public boolean hasRoll() {
        return roll;
    }

    public boolean isAI() {
        return isAI;
    }

    public Map<Token, Integer> getTokenToPositionOnMap() {
        return tokenToPositionOnMap;
    }

    public Color getColor() {
        return color;
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

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public void getPositionToken() {
        if (tokens == null || tokens.isEmpty()) {
            System.out.println("No tokens available for this player.");
            return;
        }
        System.out.println("Token ID | Position");
        System.out.println("--------------------------");

        for (Token token : tokens) {
            Integer position = tokenToPosition.getOrDefault(token, null);
            String positionString;

            if (token.isHome()) {
                positionString = "In Home";
            } else if (position != null) {
                positionString = String.valueOf(position);
            } else {
                positionString = "Not Out Yet";
            }

            // Debugging output
            System.out.println("   " + token.getId() + "    | " + positionString);
        }
    }







    public void setTokenColorsToPlayerColor() {
        tokenToPosition.keySet().forEach(Token::setColor);
    }




    public int chooseToken() {
        Scanner sc = new Scanner(System.in);

        if (tokensOut.size() == 1) {
            return tokensOut.get(0).getId();
        } else if (tokensOut.isEmpty()) {
            System.out.println("There is no token out yet.");
            return -1;
        } else {
            System.out.println("Choose the token that you want to move(insert the number)");
            for (Token t : tokensOut) {
                System.out.println("Token n." + t.getId());
            }
            System.out.print("-->");
            int choice = checkValidInput();
            while (isValidTokenChoice(choice)) {
                System.out.println("The number that you have inserted is not valid. Insert a valid one.");
                System.out.print("-->");
                choice = sc.nextInt();
            }
            return choice;
        }

    }

    public static int checkValidInput() {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            try {
                choice = sc.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("That's not an integer. Please try again.");
                sc.next();
                System.out.print("-->");
            }
        }
        return choice;
    }


    private boolean isValidTokenChoice(int choice) {
        if (choice > 5 || choice <= 0) {
            return true;
        }

        if (tokensOut.stream().noneMatch(t -> t.getId() == choice)) {
            return true;
        }
        return tokensOut.stream().noneMatch(t -> t.getId() == choice && !t.isHome());
    }


    public boolean isValidTakeTokenOut(int choice) {
        for (Token token : tokenToPosition.keySet()) {
            if (token.getId() == choice && tokenToPosition.get(token) != null) {
                return false;
            }
        }
        return tokens.stream().anyMatch(t -> t.getId() == choice && !t.isHome());
    }


    public void takeTokenOut() {
        System.out.println("Choose the token that you want to take out (insert the number):");
        List<Token> tokens = new ArrayList<>(tokenToPosition.keySet());
        tokens.sort(Comparator.comparingInt(Token::getId));

        for (Token t : tokens) {
            if (tokenToPosition.get(t) == null) {
                System.out.println("Token n." + t.getId());
            }
        }
        System.out.print("-->");
        int choice = checkValidInput();
        while (!isValidTakeTokenOut(choice)) {
            System.out.println("The number that you have inserted is not valid. Insert a valid one.");
            System.out.print("-->");
            choice = checkValidInput();
        }
        for (Token t : tokenToPosition.keySet()) {
            if (t.getId() == choice) {
                t.setPosition(0);
                tokenToPosition.put(t, 0);
                Integer pos = startingPos.remove(0);
                tokenToPositionOnMap.put(t, pos);
                t.setPositionOnMap(pos);
                t.setStartingPos(pos);
                tokensOut.add(t);
            }
        }
        System.out.println("Token " + choice + " has been taken out.");
    }


    public boolean moveToken() {
        setRoll(true);
        while (hasRoll()) {
            int diceRoll;
            if (hasPerkDecideDoubleRoll()) {
                System.out.println("Using the 'Decide Double Roll' perk");
                diceRoll = DecideDoubleRoll.chooseRoll();
                setPerkDecideDoubleRoll(false);
                setRoll(false);
            } else if (hasPerkDoubleRoll()) {
                System.out.println("Using the 'Double Roll' perk...");
                diceRoll = DoubleRoll.useDoubleRoll();
                setPerkDoubleRoll(false);
                setRoll(false);
            } else if (hasPerkBoostRoll()) {
                System.out.println("Using the 'Boost Roll' perk...");
                diceRoll = BoostRoll.rollAndBoost();
                setPerkBoostRoll(false);
                setRoll(false);
            } else {
                diceRoll = Dice.roll();
                System.out.println("\"" + name + "\"" + " rolled a " + diceRoll);
                System.out.println();
            }
            if (isNoTokenOut()) {
                if (diceRoll != 6) {
                    setRoll(false);
                } else {
                    takeTokenOut();
                    System.out.println();
                    setNoTokenOut(false);
                }
            } else {
                if (diceRoll == 6) {
                    boolean validMove = false;
                    if (!isValidMove(diceRoll) && tokenToPosition.values().stream().allMatch(Objects::nonNull)) {
                        System.out.println("No move allowed in this turn.");
                        setRoll(false);
                        validMove = true;
                    }
                    while (!validMove) {
                        displayChoices();
                        boolean validChoice = false;
                        while (!validChoice) {
                            System.out.print("-->");
                            switch (checkValidInput()) {
                                case 1:
                                    if (isValidMove(diceRoll)) {
                                        System.out.print("-->");
                                        int choice = chooseToken();
                                        while (isValidTokenChoice(choice)) {
                                            System.out.println("The number that you have inserted is not valid. Insert a valid one.");
                                            System.out.print("-->");
                                            choice = checkValidInput();
                                        }
                                        updateTokenPosition(choice, diceRoll);
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
                                    if (tokenToPosition.values().stream().allMatch(Objects::nonNull)) {
                                        System.out.println("All tokens are already out. Make another choice.");
                                        System.out.println();
                                        displayChoices();
                                    } else {
                                        takeTokenOut();
                                        validChoice = true;
                                        validMove = true;
                                    }
                                    break;
                                default:
                                    System.out.println("Insert a valid number.");
                            }
                        }
                    }
                } else {
                    if (isValidMove(diceRoll)) {
                        int choice = chooseToken();
                        while (isValidTokenChoice(choice)) {
                            System.out.println("The number that you have inserted is not valid. Insert a valid one.");
                            System.out.print("-->");
                            choice = checkValidInput();
                        }
                        updateTokenPosition(choice, diceRoll);
                        setRoll(false);
                    } else {
                        System.out.println("No move allowed in this turn.");
                        setRoll(false);
                    }

                }
            }
        }

        return false;
    }

    public void displayChoices() {
        System.out.println("Do you want to either move a token or to take out one? (Insert the number)");
        System.out.println("1. Move a token.");
        System.out.println("2. Take out a token.");
    }

    public boolean isValidMove(int diceRoll) {
        return tokensOut.stream()
                .anyMatch(token -> {
                    Integer position = tokenToPosition.get(token);
                    return position != null && position + diceRoll <= Game.getCells() - 1;
                });
    }

    public void update(Token t, int rollResult) {
        tokenToPosition.put(t, t.getPosition() + rollResult);
        int result = tokenToPositionOnMap.get(t) + rollResult;
        tokenToPositionOnMap.put(t, result);
        t.setPositionOnMap(result);
        if (tokenToPositionOnMap.get(t) > Game.getCells() - 1) {
            int temp = tokenToPositionOnMap.get(t) - (Game.getCells() - 1);
            tokenToPositionOnMap.put(t, temp);
            t.setPositionOnMap(temp);
        }
        t.setPosition(t.getPosition() + rollResult);
    }


    public void updateTokenPosition(int toUpdate, int rollResult) {
        for (Token t : tokenToPosition.keySet()) {
            if (t.getId() == toUpdate) {
                if (Game.getCells() - 1 - tokenToPosition.get(t) <= 6) { //if the player is within the last 6 tiles
                    if (tokenToPosition.get(t) + rollResult == Game.getCells() - 1) {
                        update(t, rollResult);
                        t.setHome(true);
                        inHome++;
                        System.out.println("Token n." + t.getId() + " is in the home!");
                        tokensOut.remove(t);
                        if (tokensOut.isEmpty()) {
                            setNoTokenOut(true);
                        }
                    } else if (tokenToPosition.get(t) + rollResult <= Game.getCells() - 1) {
                        update(t, rollResult);
                    } else {
                        if (tokensOut.size() > 1 && !t.isHome()) {
                            System.out.println("You need to roll exactly " + (Game.getCells() - 1 - tokenToPosition.get(t)) + " to move the token to home. Make a valid choice.");
                            System.out.print("-->");
                            int newToUpdate = checkValidInput();
                            updateTokenPosition(newToUpdate, rollResult);
                        } else if (t.getPosition() == Game.getCells() - 1) {
                            System.out.println("This token is already in the base. Make a valid choice.");
                            int newToUpdate = checkValidInput();
                            updateTokenPosition(newToUpdate, rollResult);
                        } else {
                            System.out.println("You need to roll exactly " + (Game.getCells() - 1 - tokenToPosition.get(t)) + " to move the token to home.");
                        }

                    }
                } else {
                    update(t, rollResult);
                }
            }
        }
    }

    public void reset(Token token) {
        try {
            System.out.println("Resetting token: " + token.getId());
            System.out.println("Before reset -> Position: " + token.getPosition() + ", PositionOnMap: " + token.getPositionOnMap());

            // Reset token's attributes
            token.setPosition(null);
            token.setPositionOnMap(null);

            // Update collections
            tokenToPosition.put(token, null);
            tokenToPositionOnMap.put(token, null);
            tokensOut.remove(token);

            // Update token-out status
            if (tokensOut.isEmpty()) {
                setNoTokenOut(true);
            }

            // Restore starting position
            if (token.getStartingPos() != null) {
                startingPos.add(token.getStartingPos());
            }
            token.setStartingPos(null);

            System.out.println("After reset -> Position: " + token.getPosition() + ", PositionOnMap: " + token.getPositionOnMap());
            System.out.println("Token " + token.getId() + " has been reset to the start.");
        } catch (Exception e) {
            System.out.println("Error: Unable to reset the token. This token may not exist.");
        }
    }


    public void startTurn() {
        setIsTurn(true);
        System.out.println(name + "'s turn has started.");
        System.out.println();
    }

    public void endTurn() {
        System.out.println();
        setIsTurn(false);
        System.out.println(name + "'s turn has ended.");
    }
}





