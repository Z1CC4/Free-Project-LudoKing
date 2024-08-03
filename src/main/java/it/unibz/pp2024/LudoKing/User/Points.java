package it.unibz.pp2024.LudoKing.User;

import javafx.scene.effect.Light;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Points {
    private static int points;
    private static List<String> pointsHistory=new ArrayList<>();


    public static void addPoints(int points) {
        Points.points += points;
        Points.pointsHistory.add("Won: " + points + "points");
    }

    //We said that the player loses points only when his token is eaten
    public static void losePoints(int points) {
        Points.points -= points;
        Points.pointsHistory.add("Lost: " + points + "points");
    }

    public static int listPoints() {
        return Points.points;
    }

    public static List<String> getPointsHistory() {
        return Points.pointsHistory;
    }

    public static void displayHistory(){
        for(String s:pointsHistory){
            System.out.println(s);
        }
    }


    public static int calculatePointsFromPlacement(int placement) {
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

    public static int calculatePointsFromEatingTokens(int numTokensEaten) {
        // TO DO: implement the logic to calculate points based on eating tokens
        // For example:
        return numTokensEaten * 2;
    }

    public static int calculatePointsFromMinigame(String minigameName, String difficulty) {
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
