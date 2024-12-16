package it.unibz.pp2024.LudoKing.utils;

import lombok.Getter;
import lombok.Setter;

public class Token {
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private Integer position;
    @Getter
    @Setter
    private Integer positionOnMap;
    private boolean isHome;
    @Setter
    @Getter
    private Integer startingPos;

    public Token(int id, Integer position, Integer positionOnMap) {
        this.id = id;
        this.position = position;
        this.positionOnMap = positionOnMap;
        this.isHome = false;
        this.startingPos = null;
    }

    public void setColor() {
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public boolean isInStart() {
        return position == null;
    }

    public boolean canMove(int diceRoll) {
        if (isHome) return false;
        if (isInStart()) return diceRoll == 6;
        return position + diceRoll <= 57;
    }


    public void moveOut() {
        if (isInStart()) {
            position = 1;
            positionOnMap = startingPos != null ? startingPos : 0;
            System.out.println("Token " + id + " moved out of the starting area to map position " + positionOnMap + ".");
        } else {
            System.out.println("Token " + id + " is already out of the start.");
        }
    }



    public void moveForward(int diceRoll) {
        if (isHome) {
            System.out.println("Token " + id + " is already in home and cannot move.");
            return;
        }

        if (isInStart()) {
            System.out.println("Token " + id + " is still in start. Move it out first.");
            return;
        }

        position += diceRoll;

        positionOnMap = (positionOnMap + diceRoll) % 52;

        System.out.println("Token " + id + " moved forward by " + diceRoll + " steps to position " + position + " (map position: " + positionOnMap + ").");

        if (position >= 57) {
            isHome = true;
            position = null;
            positionOnMap = null;
            System.out.println("Token " + id + " has reached home!");
        }
    }

}
