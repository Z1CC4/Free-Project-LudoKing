package it.unibz.pp2024.LudoKing.GameLogic.Utils;

import it.unibz.pp2024.LudoKing.Utils.Color;

public class Token {
    private int id;//it can be 1,2,3,4.
    private Integer position;
    //private Integer positionOnMap;
    private boolean isHome;
    Color color;
    {/*Integer positionOnMap*/}
    public Token(int id, Integer position) {
        this.id = id;
        this.position = position;
        //this.positionOnMap=positionOnMap;
        this.isHome = false;
    }

    /*public Integer getPositionOnMap(){
        return positionOnMap;
    }

    public void setPositionOnMap(Integer positionOnMap){
        this.positionOnMap=positionOnMap;
    }*/

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
