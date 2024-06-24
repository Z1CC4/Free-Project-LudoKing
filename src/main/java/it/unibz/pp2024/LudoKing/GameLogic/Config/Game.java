package it.unibz.pp2024.LudoKing.GameLogic.Config;

import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.Utils.Color;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    public static void main(String[] args) {
        Map<Player, Color> playerToColor = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Welcome to the Ludoking game.");

        List<Color> colors = new ArrayList<>(List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW));
        Collections.shuffle(colors);

        System.out.println("Choose a name.");
        String name = sc.next();
        Player p1 = new Player(name, colors.remove(rand.nextInt(0, colors.size())), 0);
        playerToColor.put(p1, p1.getColor());

        //Player p2=new Bot("Bot 1",colors.remove(rand.nextInt(0,colors.size())), 0);
        //Player p3=new Bot("Bot 2",colors.remove(rand.nextInt(0,colors.size())), 0);
        //Player p4=new Bot("Bot 3",colors.remove(rand.nextInt(0,colors.size())), 0);
        //playerToColor.put(p2, p2.getColor());
        //playerToColor.put(p3, p3.getColor());
        //playerToColor.put(p4, p4.getColor());

        List<Player> players = playerToColor.keySet().stream()
                .collect(Collectors.toList());

        p1.setTokenColorsToPlayerColor();
        //p2.setTokenColorsToPlayerColor();
        //p3.setTokenColorsToPlayerColor();
        //p4.setTokenColorsToPlayerColor();

        playerToColor.forEach((player, color) -> System.out.println(player.getName() + " is assigned the color " + color));

        while (!gameFinished(players)) {

        }


    }


    public static boolean gameFinished(List<Player> players) {
        boolean allFinished = players.stream()
                .allMatch(Player::getHasFinished);
        return allFinished;
    }

    public static Player checkWinner() {
        return null;
    }


    public static void startMiniGame(Player p) {

    }


}
