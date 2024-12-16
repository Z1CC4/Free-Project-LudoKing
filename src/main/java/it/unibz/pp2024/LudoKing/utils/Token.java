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
        this.positionOnMap = positionOnMap != null ? positionOnMap : 0;
        this.isHome = false;
        this.startingPos = null;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setColor() {
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public boolean isInStart() {
        return position == null;
    }

    public boolean canMove(int diceRoll) {
        if (this.position == null) {
            this.position = 0;
        }

        if (this.position == 0 && diceRoll == 6) {
            return true;
        }

        return this.position > 0;
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

        if (positionOnMap == null) {
            positionOnMap = 0;
        }

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
