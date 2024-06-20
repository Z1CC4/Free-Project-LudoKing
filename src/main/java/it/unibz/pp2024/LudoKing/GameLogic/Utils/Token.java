package it.unibz.pp2024.LudoKing.GameLogic.Utils;

import it.unibz.pp2024.LudoKing.Utils.Color;
import lombok.Getter;
import lombok.Setter;
public class Token {
    Color color;
    public int id;//it can be 1,2,3,4.

    public int position;
    public boolean isHome;

    public Token(int id, int position){
        this.id=id;
        this.position=position;
        this.isHome=false;
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

    public void setHome(boolean home) {
        isHome = home;
    }

    public boolean isHome() {
        return isHome;
    }
}
