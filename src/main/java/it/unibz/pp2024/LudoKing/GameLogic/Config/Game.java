package it.unibz.pp2024.LudoKing.GameLogic.Config;

import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.Utils.Color;

import javax.net.ssl.SSLContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {


    private static final int cells=64;

    public int getCells(){
        return cells;
    }


    public static void ludoKing() {
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


        int[] board=new int[cells];

        List<Integer> uniqueNumbers = IntStream.generate(() -> rand.nextInt(cells))
                .distinct()
                .limit(12)
                .boxed()
                .collect(Collectors.toList());



        int miniGameOne=uniqueNumbers.get(0);
        int miniGameTwo=uniqueNumbers.get(1);
        int miniGameThree=uniqueNumbers.get(2);
        int miniGameFour=uniqueNumbers.get(3);
        int miniGameFive=uniqueNumbers.get(4);
        int miniGameSix=uniqueNumbers.get(5);
        int miniGameSeven=uniqueNumbers.get(6);
        int miniGameEight=uniqueNumbers.get(7);
        int miniGameNine=uniqueNumbers.get(8);
        int miniGameTen=uniqueNumbers.get(9);
        int miniGameEleven=uniqueNumbers.get(10);
        int miniGameTwelve=uniqueNumbers.get(11);



        while (!gameFinished(players)) {
            for(Player p:playerToColor.keySet()){
                if(p.getHasFinished()){
                    continue;
                }
                p.startTurn();

                p.endTurn();
            }
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

    public static void menu(Player p){
        Scanner sc=new Scanner(System.in);
        boolean valid=false;
        while(!valid){
            System.out.println("Select your choice(Enter the number).");
            System.out.println("1.Roll the dice. (Your turn ends)");
            System.out.println("2.Get position of a specific token. (Your turn will not end)");
            System.out.println("3.Show points history. (Your turn will not end)");
            System.out.println("4.Show ranking chart. (Your turn will not end)");
            switch(sc.nextInt()){
                case 1:
                    p.moveToken();
                    valid=true;
                    break;
                case 2:
                    p.getPositionToken();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    System.out.println("Invalid choice. Insert one of the number on the screen..");
            }
        }

    }



}
