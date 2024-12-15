package it.unibz.pp2024.LudoKing.utils;

public class Token {
    private int id; // Can be 1, 2, 3, 4
    private Integer position;
    private Integer positionOnMap;
    private boolean isHome;
    private Integer startingPos;
    private Color color;

    public Token(int id, Integer position, Integer positionOnMap) {
        this.id = id;
        this.position = position;
        this.positionOnMap = positionOnMap;
        this.isHome = false;
        this.startingPos = null;
    }

    public Integer getStartingPos() {
        return startingPos;
    }

    public void setStartingPos(Integer startingPos) {
        this.startingPos = startingPos;
    }

    public Integer getPositionOnMap() {
        return positionOnMap;
    }

    public void setPositionOnMap(Integer positionOnMap) {
        this.positionOnMap = positionOnMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }
    public boolean isInStart() {
        return position == null; // Token is in the starting area if position is null
    }

    public boolean canMove(int diceRoll) {
        if (isHome) return false; // Token can't move if it is already home
        if (isInStart() && diceRoll != 6) return false; // Tokens in start can only move with a 6
        // Additional conditions to prevent invalid moves (e.g., beyond board limits) can be added here
        return true;
    }

    public void moveOut() {
        if (isInStart()) {
            position = 0; // Start position (adjust as per your board rules)
            positionOnMap = startingPos;
            System.out.println("Token " + id + " moved out of the starting area.");
        }
    }


    public void moveForward(int diceRoll) {
    }
}
