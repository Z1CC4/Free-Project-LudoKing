package it.unibz.pp2024.LudoKing.GameLogic.Utils;

import it.unibz.pp2024.LudoKing.Utils.Color;

public class Token {
    private int id;
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

    public Integer getPositionOnMap() {
        return positionOnMap;
    }

    public void setPositionOnMap(Integer positionOnMap) {
        this.positionOnMap = positionOnMap;
    }

    public Integer getStartingPos() {
        return startingPos;
    }

    public void setStartingPos(Integer startingPos) {
        this.startingPos = startingPos;
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

    public void setHome(boolean isHome) {
        this.isHome = isHome;
    }
}
