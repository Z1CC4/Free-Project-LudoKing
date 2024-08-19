package it.unibz.pp2024.LudoKing.User;

import javafx.scene.effect.Light;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Points {
    private int points;
    private List<String> pointsHistory = new ArrayList<>();


    public void addPoints(int points) {
        this.points += points;
        pointsHistory.add("Won: " + points + "points");
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    //We said that the player loses points only when his token is eaten
    public void losePoints(int points) {
        this.points -= points;
        pointsHistory.add("Lost: " + points + "points");
    }

    public List<String> getPointsHistory() {
        return pointsHistory;
    }

    public void displayHistory() {
        if (!getPointsHistory().isEmpty()) {
            for (String s : pointsHistory) {
                System.out.println(s);
            }
        } else {
            System.out.println("No points gained yet.");
        }

    }
}