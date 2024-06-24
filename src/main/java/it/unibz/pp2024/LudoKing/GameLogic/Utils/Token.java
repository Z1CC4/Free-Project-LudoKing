package it.unibz.pp2024.LudoKing.GameLogic.Utils;

import it.unibz.pp2024.LudoKing.Utils.Color;

public class Token {
    public int id;//it can be 1,2,3,4.
    public int position;
    public boolean isHome;
    Color color;

    public Token(int id, int position) {
        this.id = id;
        this.position = position;
        this.isHome = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
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
