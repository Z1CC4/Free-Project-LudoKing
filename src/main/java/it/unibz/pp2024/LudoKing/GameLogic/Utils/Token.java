package it.unibz.pp2024.LudoKing.GameLogic.Utils;

import it.unibz.pp2024.LudoKing.Utils.Color;

public class Token {
    private int id;//it can be 1,2,3,4.
    private Integer position;
    private Integer positionOnMap;
    private boolean isHome;

    private Integer startingPos;
    Color color;

    public Token(int id, Integer position, Integer positionOnMap) {
        this.id = id;
        this.position = position;
        this.positionOnMap = positionOnMap;
        this.isHome = false;
        this.startingPos=null;
    }

    public Integer getStartingPos() {
        return startingPos;
    }

    public void setStartingPos(Integer startingPos) {
        this.startingPos = startingPos;
    }

    public Integer getPositionOnMap(){
        return positionOnMap;
    }

    public void setPositionOnMap(Integer positionOnMap){
        this.positionOnMap=positionOnMap;
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
}