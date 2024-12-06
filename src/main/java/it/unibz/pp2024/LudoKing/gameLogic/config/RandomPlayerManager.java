package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.ArrayList;
import java.util.List;

public class RandomPlayerManager {
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
