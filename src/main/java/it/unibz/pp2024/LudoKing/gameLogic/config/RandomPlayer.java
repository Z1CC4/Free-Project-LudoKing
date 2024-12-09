package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.Arrays;

public class RandomPlayer {

    private final String name;
    private final boolean isAI;
    private final int[] tokens;
    private int tokensInside;
    private boolean hasFinished;

    public static final int TOKENS_PER_PLAYER = 4;
    public static final int FINAL_POSITION = 52;

    public RandomPlayer(String name, boolean isAI) {
        this.name = name;
        this.isAI = isAI;
        this.tokens = new int[TOKENS_PER_PLAYER];
        this.tokensInside = 0;
        this.hasFinished = false;
        Arrays.fill(tokens, -1);
    }

    public String getName() {
        return name;
    }

    public boolean isAI() {
        return isAI;
    }

    public boolean hasFinished() {
        return hasFinished;
    }

    public void markFinished() {
        hasFinished = true;
    }

    public int tokensOut() {
        return (int) Arrays.stream(tokens).filter(pos -> pos != -1 && pos != FINAL_POSITION).count();
    }

    public int tokensInside() {
        return tokensInside;
    }

    public boolean isNoTokenOut() {
        return tokensOut() == 0;
    }

    public void takeTokenOut() {
        for (int i = 0; i < TOKENS_PER_PLAYER; i++) {
            if (tokens[i] == -1) {
                tokens[i] = 0;
                break;
            }
        }
    }

    public int getTokenPosition(int tokenIndex) {
        return tokens[tokenIndex];
    }

    public void updateTokenPosition(int tokenIndex, int newPosition) {
        tokens[tokenIndex] = newPosition;
    }

    public void markTokenInside(int tokenIndex) {
        tokens[tokenIndex] = FINAL_POSITION;
        tokensInside++;
    }
}
