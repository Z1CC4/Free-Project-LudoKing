package it.unibz.pp2024.LudoKing.GameLogic.Bots;

import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.Perks.DecideDoubleRoll;
import it.unibz.pp2024.LudoKing.Perks.DoubleRoll;
import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.Utils.Color;

import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDoubleRoll;
import static it.unibz.pp2024.LudoKing.MiniGames.GuessTheWord.hasPerkDecideDoubleRoll;

public class RandomGenerator extends Player {
    private Random random = new Random();

    public RandomGenerator(String name, Color color, int inHome) {
        super(name, color, inHome);
    }

    @Override
    public int chooseToken() {
        List<Token> availableTokens = getTokens().stream()
                .filter(token -> !token.isHome())
                .collect(Collectors.toList());
        return availableTokens.get(random.nextInt(availableTokens.size())).getId();
    }

    @Override
    public void takeTokenOut() {
        List<Token> availableTokens = getTokens().stream()
                .filter(token -> token.getPosition() == null)
                .collect(Collectors.toList());
        Token token = availableTokens.get(random.nextInt(availableTokens.size()));
        token.setPosition(0);
        tokenToPosition.put(token, 0);
        Integer pos = startingPos.remove(0);
        tokenToPositionOnMap.put(token, pos);
        token.setStartingPos(pos);
        System.out.println("Token " + token.getId() + " has been taken out.");
    }

    @Override
    public void moveToken() {
        int diceRoll = Dice.roll();
        setRoll(true);
        System.out.println(getName() + " rolled a " + diceRoll);

        if (isNoTokenOut()) {
            if (diceRoll == 6) {
                takeTokenOut();
                setNoTokenOut(false);
            } else {
                setRoll(false);
            }
        } else {
            if (diceRoll == 6 && isAnyTokenPositionNull()) {
                int choice = random.nextInt(2);
                if (choice == 0) {
                    int tokenChoice = chooseToken();
                    updateTokenPosition(tokenChoice, diceRoll);
                    checkIsHome(tokenChoice);
                } else {
                    takeTokenOut();
                }
            } else {
                setRoll(false);
            }
        }
    }

    @Override
    public void usePerkBoostRoll() {
        if (hasPerkBoostRoll()) {
            if (random.nextBoolean()) {
                BoostRoll.rollAndBoost();
            }
        }
    }

    @Override
    public void usePerkDecideDoubleRoll() {
        if (hasPerkDecideDoubleRoll()) {
            if (random.nextBoolean()) {
                DecideDoubleRoll.chooseRoll();
            }
        }
    }

    @Override
    public void useDoubleRoll() {
        if (hasPerkDoubleRoll()) {
            if (random.nextBoolean()) {
                DoubleRoll.useDoubleRoll();
            }
        }
    }
}