package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.ArrayList;
import java.util.List;

public class RandomPlayerManager {
    private final List<RandomPlayer> players;

    public RandomPlayerManager() {
        players = new ArrayList<>();
    }

    // Add a player to the list
    public void addPlayer(String name, boolean isAI) {
        players.add(new RandomPlayer(name, isAI));
    }

    // Get all players
    public List<RandomPlayer> getAllPlayers() {
        return players;
    }
}
