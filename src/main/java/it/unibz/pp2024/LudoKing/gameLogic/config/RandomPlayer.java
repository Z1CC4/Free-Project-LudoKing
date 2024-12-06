package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.ArrayList;
import java.util.List;

public class RandomPlayer {
    private final String name;
    private final boolean isAI;
    private final int[] tokens;
    private boolean hasFinished;

    public static final int TOKENS_PER_PLAYER = 4; // Define the number of tokens
    public static final int BOARD_SIZE = 100; // Example board size

    public RandomPlayer(String name, boolean isAI) {
        this.name = name;
        this.isAI = isAI;
        this.tokens = new int[TOKENS_PER_PLAYER];
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = -1; // Initialize tokens in house (-1)
        }
        this.hasFinished = false;
    }

    // Getters and player-related methods
    public String getName() {
        return name;
    }

    public boolean isAI() {
        return isAI;
    }

    public boolean isNoTokenOut() {
        return java.util.Arrays.stream(tokens).allMatch(pos -> pos == -1);
    }

    public void takeTokenOut() {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == -1) { // Token in house
                tokens[i] = 0; // Move to start
                break;
            }
        }
    }

    public int getTokenPosition(int index) {
        return tokens[index];
    }

    public void updateTokenPosition(int index, int newPosition) {
        tokens[index] = newPosition;
    }

    public boolean hasAllTokensFinished() {
        return java.util.Arrays.stream(tokens).allMatch(pos -> pos == BOARD_SIZE - 1);
    }

    public void handleMiniGameLoss(int tokenIndex) {
        tokens[tokenIndex] = -1; // Return to house on mini-game loss
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public boolean hasFinished() {
        return hasFinished;
    }

    // Manage multiple RandomPlayers
    public static class RandomPlayerManager {
        private final List<RandomPlayer> players;

        public RandomPlayerManager() {
            this.players = new ArrayList<>();
        }

        // Add a player to the list
        public void addPlayer(String name, boolean isAI) {
            players.add(new RandomPlayer(name, isAI));
        }

        // Get a player by index
        public RandomPlayer getPlayer(int index) {
            return players.get(index);
        }

        // Get all players
        public List<RandomPlayer> getAllPlayers() {
            return new ArrayList<>(players);
        }

        // Display all players
        public void displayPlayers() {
            System.out.println("Players in the game:");
            for (int i = 0; i < players.size(); i++) {
                RandomPlayer player = players.get(i);
                System.out.println((i + 1) + ". " + player.getName() + " (AI: " + player.isAI() + ")");
            }
        }

        // Check if all players have finished the game
        public boolean allPlayersFinished() {
            return players.stream().allMatch(RandomPlayer::hasFinished);
        }

        // Get the player with the highest rank (finished first)
        public RandomPlayer getHighestRankingPlayer() {
            return players.stream()
                    .filter(RandomPlayer::hasFinished)
                    .findFirst()
                    .orElse(null);
        }

        // Example usage of a specific player method (e.g., moveToken)
        public void movePlayerToken(int playerIndex, int tokenIndex, int diceRoll) {
            RandomPlayer player = players.get(playerIndex);
            int oldPosition = player.getTokenPosition(tokenIndex);
            int newPosition = (oldPosition + diceRoll) % RandomPlayer.BOARD_SIZE;
            player.updateTokenPosition(tokenIndex, newPosition);
            System.out.println(player.getName() + " moves token " + tokenIndex + " from " + oldPosition + " to " + newPosition + ".");
        }
    }
}
