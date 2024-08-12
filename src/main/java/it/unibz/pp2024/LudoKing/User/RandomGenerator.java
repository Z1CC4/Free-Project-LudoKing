package it.unibz.pp2024.LudoKing.User;

import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.Utils.Dice;
import it.unibz.pp2024.LudoKing.Utils.Color;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

public class RandomGenerator extends Player<RandomGenerator> {
    @Getter
    @Setter
    private String name;
    private Color color;
    private int inHome;
    private boolean roll;
    private boolean noTokenOut;
    private List<Token> tokens;
    private Map<Token, Integer> tokenToPosition;
    private Map<Token, Integer> tokenToPositionOnMap;
    @Getter
    private List<Integer> startingPos;

    public RandomGenerator(String name, Color color, int inHome) {
        super(name, color, inHome);
        this.name = name;
        this.color = color;
        this.inHome = inHome;
        this.roll = false;
        this.noTokenOut = true;
        this.tokens = new ArrayList<>();
        this.tokenToPosition = new HashMap<>();
        this.tokenToPositionOnMap = new HashMap<>();
        this.startingPos = new ArrayList<>();
    }

    @Override
    public void getPositionToken() {
        List<Token> availableTokens = new ArrayList<>();
        for (Token token : getTokens()) {
            if (!token.isHome()) {
                availableTokens.add(token);
            }
        }
        Token randomToken = availableTokens.get(new Random().nextInt(availableTokens.size()));
        System.out.println("The token is at position " + getTokenToPosition().get(randomToken));
    }

    @Override
    public void takeTokenOut() {
        List<Token> availableTokens = new ArrayList<>();
        for (Token token : getTokens()) {
            if (!token.isHome()) {
                availableTokens.add(token);
            }
        }
        Token randomToken = availableTokens.get(new Random().nextInt(availableTokens.size()));
        getTokenToPosition().put(randomToken, 0);
        Integer pos = getStartingPos().remove(0);
        getTokenToPositionOnMap().put(randomToken, pos);
        randomToken.setStartingPos(pos);
        System.out.println("Token " + randomToken.getId() + " has been taken out.");
    }

    @Override
    public void moveToken() {
        setRoll(true);

        while (getRoll()) {
            int diceRoll = Dice.roll();
            System.out.println(getName() + " rolled a " + diceRoll);

            if (isNoTokenOut()) {
                if (diceRoll != 6) {
                    setRoll(false);
                } else {
                    takeTokenOut();
                    setNoTokenOut(false);
                }
            } else {
                if (diceRoll == 6 && isAnyTokenPositionNull()) {
                    if (new Random().nextBoolean()) {
                        List<Token> availableTokens = new ArrayList<>();
                        for (Token token : getTokens()) {
                            if (!token.isHome()) {
                                availableTokens.add(token);
                            }
                        }
                        int choice = availableTokens.get(new Random().nextInt(availableTokens.size())).getId();
                        Scanner scanner = new Scanner(System.in);
                        updateTokenPosition(choice, diceRoll,scanner);
                        checkIsHome(choice);
                    } else {
                        takeTokenOut();
                    }
                }
                if (diceRoll != 6) {
                    setRoll(false);
                }
            }
        }
    }

    @Override
    public int chooseToken() {
        List<Token> availableTokens = new ArrayList<>();
        for (Token token : getTokens()) {
            if (!token.isHome()) {
                availableTokens.add(token);
            }
        }
        return availableTokens.get(new Random().nextInt(availableTokens.size())).getId();
    }
}