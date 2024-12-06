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
    private static final List<Integer> miniGamePositions = List.of(5, 10, 20, 30, 50); // Example mini-game positions

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

        // Example usage of a specific player method (e.g., moveToken)
        public void movePlayerToken(int playerIndex, int tokenIndex, int diceRoll) {
            RandomPlayer player = players.get(playerIndex);
            int oldPosition = player.getTokenPosition(tokenIndex);
            int newPosition = (oldPosition + diceRoll) % RandomPlayer.BOARD_SIZE;
            player.updateTokenPosition(tokenIndex, newPosition);
            System.out.println(player.getName() + " moves token " + tokenIndex + " from " + oldPosition + " to " + newPosition + ".");
        }

        // No mini-game logic: just simple linear movement
        public void playerTurn(RandomPlayer player) {
            int diceRoll = (int) (Math.random() * 6) + 1;
            System.out.println(player.getName() + " rolled a " + diceRoll);

            // Take a token out or move an existing token
            if (player.isNoTokenOut() && diceRoll == 6) {
                player.takeTokenOut();
                System.out.println(player.getName() + " took a token out!");
            } else {
                moveToken(player, diceRoll);
            }
        }

        // Movement of a token without mini-games or avoiding locations
        public void moveToken(RandomPlayer player, int diceRoll) {
            // Choose the first available token to move
            for (int i = 0; i < TOKENS_PER_PLAYER; i++) {
                if (player.getTokenPosition(i) != -1) {
                    int oldPosition = player.getTokenPosition(i);
                    int newPosition = (oldPosition + diceRoll) % BOARD_SIZE;

                    // Check for eating mechanism: check if any other player's token occupies the new position
                    for (RandomPlayer otherPlayer : players) {
                        if (otherPlayer != player) {
                            for (int j = 0; j < TOKENS_PER_PLAYER; j++) {
                                if (otherPlayer.getTokenPosition(j) == newPosition) {
                                    // If another player's token is at the same position, "eat" their token
                                    System.out.println(player.getName() + " eats " + otherPlayer.getName() + "'s token at position " + newPosition);
                                    otherPlayer.updateTokenPosition(j, -1); // Send the other token back to the house
                                }
                            }
                        }
                    }

                    // Check if the new position is a mini-game spot
                    if (miniGamePositions.contains(newPosition)) {
                        System.out.println(player.getName() + " landed on a mini-game spot at position " + newPosition);
                        // Ask if the player wants to accept the mini-game
                        boolean acceptsMiniGame = askToAcceptMiniGame(player);
                        if (acceptsMiniGame) {
                            playMiniGame(player);
                        }
                    }

                    // Move the player's token
                    player.updateTokenPosition(i, newPosition);
                    System.out.println(player.getName() + " moved token " + i + " to position " + newPosition);
                    break; // Move one token at a time
                }
            }
        }

        // Check if a player has finished the game
        public boolean checkForWinner() {
            return players.stream().anyMatch(RandomPlayer::hasAllTokensFinished);
        }

        // Method to ask the player if they want to accept the mini-game
        private boolean askToAcceptMiniGame(RandomPlayer player) {
            // In a real game, here you would ask the player interactively.
            // Since we are in a simplified setup, for now, we'll randomly decide if the AI accepts.
            if (player.isAI()) {
                // AI always accepts the mini-game (for simplicity)
                return true;
            } else {
                // For now, let's assume the player always accepts the mini-game.
                // This could be replaced with user input or other decision-making logic.
                return true;
            }
        }

        // Method to handle mini-game logic
        private void playMiniGame(RandomPlayer player) {
            // You would invoke the mini-game logic from another class here
            // For demonstration, we'll call a placeholder method
            System.out.println(player.getName() + " is playing a mini-game...");
            // Assuming you have a class `MiniGame` with a static method `play`
            // MiniGame.play(player);
        }

        // Improved code
        public void startGame() {
            while (!allPlayersFinished()) {
                for (RandomPlayer player : players) {
                    playerTurn(player);
                    if (checkForWinner()) {
                        System.out.println("Game over! " + player.getName() + " wins!");
                        return;
                    }
                }
            }
        }
    }
}