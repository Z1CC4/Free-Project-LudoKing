package it.unibz.pp2024.LudoKing.User;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Points {
    protected int points;
    protected List<String> pointsHistory;

    public Points() {
        this.points = 0;
        this.pointsHistory = new ArrayList<>();
    }

    public void addPoints(int points) {
        this.points += points;
        this.pointsHistory.add("Gain: " + points);
    }

    public void losePoints(int points) {
        this.points -= points;
        this.pointsHistory.add("Loss: " + points);
    }

    public int listPoints() {
        return this.points;
    }

    public List<String> historyPoints() {
        return this.pointsHistory;
    }

    public int calculatePointsFromPlacement(int placement) {
        // TO DO: implement the logic to calculate points based on placement
        // For example:
        if (placement == 1) {
            return 10;
        } else if (placement == 2) {
            return 5;
        } else {
            return 0;
        }
    }

    public int calculatePointsFromEatingTokens(int numTokensEaten) {
        // TO DO: implement the logic to calculate points based on eating tokens
        // For example:
        return numTokensEaten * 2;
    }

    public int calculatePointsFromMinigame(String minigameName, String difficulty) {
        // TO DO: implement the logic to calculate points based on minigame and difficulty
        // For example:
        if (minigameName.equals("MiniGame1") && difficulty.equals("easy")) {
            return 5;
        } else if (minigameName.equals("MiniGame1") && difficulty.equals("hard")) {
            return 10;
        }
        //...
        return 0;
    }
}