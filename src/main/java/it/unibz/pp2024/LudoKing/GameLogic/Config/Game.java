package it.unibz.pp2024.LudoKing.GameLogic.Config;

import com.almasb.fxgl.scene.SceneService;
import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.Utils.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {

    public static void main(String[] args){
        Map<Player, Color> playerToColor=new HashMap<>();
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to the Ludoking game.");
        System.out.println("Choose a name.");
        String name=sc.next();

        //Player p1=new Player();
        //List<Player> players=List.of(p1,p2,p3,p4);
    }


}
