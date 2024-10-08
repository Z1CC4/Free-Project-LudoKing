package it.unibz.pp2024.LudoKing.user;
import it.unibz.pp2024.LudoKing.utils.Token;
import it.unibz.pp2024.LudoKing.utils.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkBoostRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDoubleRoll;
import static it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.QuizPerkUtil.hasPerkDecideDoubleRoll;*/

public class RandomGenerator extends Player {
    private Random random = new Random();

    public RandomGenerator(String name, Color color, int inHome) {
        super(name, color, inHome);
    }

    @Override
    public int chooseToken() {
        List<Token> availableTokens = new ArrayList<>();
        for (Object token : getTokens()) {
            Token tokenObj = (Token) token;
            if (!tokenObj.isHome()) {
                availableTokens.add(tokenObj);
            }
        }
        return availableTokens.get(random.nextInt(availableTokens.size())).getId();
    }

    @Override
    public void takeTokenOut() {
        List<Token> availableTokens = new ArrayList<>();
        for (Object token : getTokens()) {
            Token tokenObj = (Token) token;
            if (tokenObj.getPosition() == null) {
                availableTokens.add(tokenObj);
            }
        }
        Token token = availableTokens.get(random.nextInt(availableTokens.size()));
        token.setPosition(0);
        Integer pos = (Integer) startingPos.remove(0);
        token.setStartingPos(pos);
        System.out.println("Token " + token.getId() + " has been taken out.");
    }

    /*public void usePerkBoostRoll() {
        if (hasPerkBoostRoll()) {
            if (random.nextBoolean()) {
                BoostRoll.rollAndBoost();
            }
        }
    }

    public void usePerkDecideDoubleRoll() {
        if (hasPerkDecideDoubleRoll()) {
            if (random.nextBoolean()) {
                DecideDoubleRoll.chooseRoll();
            }
        }
    }


    public void usePerkDoubleRoll() {
        if (hasPerkDoubleRoll()) {
            if (random.nextBoolean()) {
                DoubleRoll.useDoubleRoll();
            }
        }
    }*/
}