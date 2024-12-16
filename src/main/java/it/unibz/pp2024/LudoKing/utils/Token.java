package it.unibz.pp2024.LudoKing.utils;

import lombok.Getter;
import lombok.Setter;

public class Token {
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private Integer position;  // The position on the board
    @Getter
    @Setter
    private Integer positionOnMap;  // Position on the map (from 0 to 51)
    private boolean isHome;  // Whether the token has reached home
    @Setter
    @Getter
    private Integer startingPos;  // The token's starting position

    public Token(int id, Integer position, Integer positionOnMap) {
        this.id = id;
        this.position = position;
        this.positionOnMap = positionOnMap != null ? positionOnMap : 0;  // Ensure positionOnMap is never null
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
        // If position is null, initialize it to 0 (start position)
        if (this.position == null) {
            this.position = 0;
        }

        // Token can move out from start only if the dice roll is 6
        if (this.position == 0 && diceRoll == 6) {
            return true;
        }

        return this.position > 0;  // Token can move if it’s already out
    }

    public void moveForward(int diceRoll) {
        // If the token is home, it cannot move
        if (isHome) {
            System.out.println("Token " + id + " is already in home and cannot move.");
            return;
        }

        // If token is in start position, it needs to be moved out first
        if (isInStart()) {
            System.out.println("Token " + id + " is still in start. Move it out first.");
            return;
        }

        // Update position and positionOnMap
        position += diceRoll;

        // Ensure positionOnMap is initialized before adding the dice roll
        if (positionOnMap == null) {
            positionOnMap = 0;  // Initialize positionOnMap if null
        }

        // Move forward on the map with wrapping around the board if needed (52 positions)
        positionOnMap = (positionOnMap + diceRoll) % 52;

        System.out.println("Token " + id + " moved forward by " + diceRoll + " steps to position " + position + " (map position: " + positionOnMap + ").");

        // If the token reaches or exceeds position 57, it has reached home
        if (position >= 57) {
            isHome = true;
            position = null;
            positionOnMap = null;
            System.out.println("Token " + id + " has reached home!");
        }
    }
}
