package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.ArrayList;
import java.util.List;

public class RandomPlayerManager {

    private final List<RandomPlayer> players;

    public RandomPlayerManager() {
        players = new ArrayList<>();
    }

    public void addPlayer(String name, boolean isAI) {
        players.add(new RandomPlayer(name, isAI));
    }

    public List<RandomPlayer> getAllPlayers() {
        return players;
    }
}
