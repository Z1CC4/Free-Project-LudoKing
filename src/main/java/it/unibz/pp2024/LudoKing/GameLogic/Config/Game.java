package it.unibz.pp2024.LudoKing.GameLogic.Config;

import com.almasb.fxgl.scene.SceneService;
import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.Utils.Color;

import java.util.*;

public class Game {

    public static void main(String[] args){
        Map<Player, Color> playerToColor=new HashMap<>();
        Scanner sc=new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Welcome to the Ludoking game.");

        List<Color> colors = new ArrayList<>(List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW));
        Collections.shuffle(colors);

        System.out.println("Choose a name.");
        String name=sc.next();
        Player p1=new Player(name, colors.get(rand.nextInt(0,3)), 0);
        playerToColor.put(p1, p1.getColor());


        //Player p1=new Player();
        //List<Player> players=List.of(p1,p2,p3,p4);
    }


}
