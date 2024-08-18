package it.unibz.pp2024.LudoKing.GameLogic.Config;

import it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.*;
import it.unibz.pp2024.LudoKing.MiniGames.GuessTheWord;
import it.unibz.pp2024.LudoKing.MiniGames.TicTacToe;
import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.Utils.Color;
import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.Utils.Placement;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;




public class Game {


    private static final int cells=64;
    //private static final int cells=8;


    public static int getCells(){
        return cells;
    }

    public static Map<Player, Placement> playerToPlacement=new HashMap<>();
    public static List<Placement> placements=new ArrayList<>(List.of(Placement.FIRST, Placement.SECOND, Placement.THIRD, Placement.FOURTH));

    public static Map<Player, Color> playerToColor = new HashMap<>();

    public static Map<MiniGame, Integer> gameToPosition=new HashMap<>();

    public static List<Player> players=new ArrayList<>();

    public static void ludoKing() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Welcome to the Ludoking game.");
        System.out.println();

        List<Color> colors = new ArrayList<>(List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW));
        Collections.shuffle(colors);

        System.out.print("Choose a name for player 1:");
        String name = sc.next();
        System.out.println();
        Player p1 = new Player(name, colors.remove(rand.nextInt(0, colors.size())), 0);
        playerToColor.put(p1, p1.getColor());
        playerToPlacement.put(p1, null);
        players.add(p1);



        System.out.print("Choose a name for player 2:");
        String name2 = sc.next();
        System.out.println();
        Player p2 = new Player(name2, colors.remove(rand.nextInt(0, colors.size())), 0);
        playerToColor.put(p2, p2.getColor());
        playerToPlacement.put(p2, null);
        players.add(p2);

        for(Placement p:placements){
            System.out.println(p);
        }
        System.out.println(placements.size());

        /*System.out.print("Choose a name for player 3:");
        String name3 = sc.next();
        System.out.println();
        Player p3 = new Player(name3, colors.remove(rand.nextInt(0, colors.size())), 0);
        playerToColor.put(p3, p3.getColor());
        playerToPlacement.put(p3, null);

        System.out.print("Choose a name for player 4:");
        String name4 = sc.next();
        System.out.println();
        Player p4 = new Player(name4, colors.remove(rand.nextInt(0, colors.size())), 0);
        playerToColor.put(p4, p4.getColor());
        playerToPlacement.put(p4, null);*/



        //Player p2=new Bot("Bot 1",colors.remove(rand.nextInt(0,colors.size())), 0);
        //playerToColor.put(p2, p2.getColor());
        //playerToPlacement.put(p2, null);

        //Player p3=new Bot("Bot 2",colors.remove(rand.nextInt(0,colors.size())), 0);
        //playerToColor.put(p3, p3.getColor());
        // playerToPlacement.put(p3, null);

        //Player p4=new Bot("Bot 3",colors.remove(rand.nextInt(0,colors.size())), 0);
        //playerToColor.put(p4, p4.getColor());
        //playerToPlacement.put(p4, null);


        List<Player> players = playerToColor.keySet().stream()
                .collect(Collectors.toList());

        p1.setTokenColorsToPlayerColor();
        //p2.setTokenColorsToPlayerColor();
        //p3.setTokenColorsToPlayerColor();
        //p4.setTokenColorsToPlayerColor();

        playerToColor.forEach((player, color) -> System.out.println("Player "+"\""+player.getName()+ "\"" + " is assigned the color " + color +"."));
        System.out.println();

        int[] board=new int[cells];

        /*List<Integer> uniqueNumbers = IntStream.generate(() -> rand.nextInt(cells-2) + 1)
                .distinct()
                .limit(12)
                .boxed()
                .collect(Collectors.toList());


        List<MiniGame> miniGames = Arrays.asList(
                new Quiz1(), new Quiz2(), new Quiz3(), new Quiz4(),
                new Quiz5(), new Quiz6(), new Quiz7(), new Quiz8(),
                new Quiz9(), new Quiz10(), new TicTacToe(), new GuessTheWord()
        );

        for (int i = 0; i < miniGames.size(); i++) {
            gameToPosition.put(miniGames.get(i), uniqueNumbers.get(i));
        }*/

        int round=0;

        while (!gameFinished(players)) {
            round++;
            System.out.println("ROUND "+round);
            System.out.println();
            for(Player p:playerToColor.keySet()){
                if(p.getHasFinished()){
                    continue;
                }
                menu(p);
            }
        }

        System.out.println(checkWinner().getName() + " has won the game!");
        rankingList();


    }


    public static boolean gameFinished(List<Player> players) {
        boolean allFinished = players.stream()
                .allMatch(Player::getHasFinished);
        return allFinished;
    }

    public static Player checkWinner() {
        return playerToPlacement.keySet().stream()
                .max(Comparator.comparingInt(p -> p.getPoints().getPoints()))
                .orElse(null);
    }


    public static void miniGame(Player p) {
        List<Token> list=p.getTokens();
        for(Token t:list){
            checkMiniGame(t, p.getTokenToPositionOnMap(), p);
        }
    }

    public static void checkMiniGame(Token t, Map<Token, Integer> tToP, Player p){
        for(Token tt:tToP.keySet()){
            if(t==tt){
                for(MiniGame m:gameToPosition.keySet()){
                    if(gameToPosition.get(m).equals(tToP.get(tt))){
                        System.out.println("pos:"+tToP.get(tt));
                        if(m.play()){
                            p.updateTokenPosition(tt.getId(), 1, new Scanner(System.in));
                            System.out.println("Your token has moved 1 position ahead.");
                        }else{
                            p.updateTokenPosition(tt.getId(), -1, new Scanner(System.in));
                            System.out.println("Your token has moved 1 position backwards.");
                        }
                        break;
                    }
                }
            }

        }
    }


    public static void displayMenu(){
        System.out.println("Select your choice(Enter the number).");
        System.out.println("1.Roll the dice. (Your turn ends)");
        System.out.println("2.Get position of a specific token. (Your turn will not end)");
        System.out.println("3.Show points history. (Your turn will not end)");
        System.out.println("4.Show ranking chart. (Your turn will not end)");
        System.out.println("5.Check token position on map of other players. (Your turn will not end)");
        System.out.print("-->");
    }


    public static void menu(Player p){
        p.startTurn();
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            displayMenu();
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        p.moveToken();
                        checkFinish(p);
                        //checkForEats(p, players);
                        //miniGame(p);
                        p.endTurn();
                        System.out.println("-->"+p.getPoints().getPoints());
                        System.out.println();
                        valid = true;
                        break;
                    case 2:
                        p.getPositionToken();
                        System.out.println();
                        break;
                    case 3:
                        showHistoryPoints(p);
                        System.out.println();
                        break;
                    case 4:
                        rankingList();
                        System.out.println();
                        break;
                    case 5:
                        showPlayersTokenPositionMap();
                        System.out.println();
                        break;
                    default:
                        System.out.println("Invalid choice. Insert one of the numbers on the screen.");
                        System.out.println();
                }
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }
    }

    public static void showPlayersTokenPositionMap(){
        for(Player p:playerToColor.keySet()){
            System.out.println("Name:"+p.getName());
            p.displayTokenPositionOnMap();
            System.out.println();
        }
    }


    public static void showHistoryPoints(Player p){
        if(p.getPoints().getPointsHistory()!=null){
            p.getPoints().displayHistory();
        }else{
            System.out.println();
            System.out.println("The history of points is empty.");
            System.out.println();
        }
    }



    public static void rankingList() {
        List<Player> sortedPlayers = playerToPlacement.keySet().stream()
                .sorted(Comparator.comparingInt(p -> -p.getPoints().getPoints()))
                .collect(Collectors.toList());

        System.out.println("Ranking List:");
        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player player = sortedPlayers.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getPoints().getPoints() + " points");
        }
    }

    public static void checkFinish(Player p) {
        int pointsToAdd=0;
        System.out.println(p.getName() + " , "+p.getInHome()+" , "+p.getHasFinished());
        if (p.getInHome() == 4 && !p.getHasFinished()) {
            p.setHasFinished(true);
            playerToPlacement.put(p, placements.remove(0));
            pointsToAdd = calculatePointsForPlacement();
            System.out.println(p.getPoints().getPoints());
            p.getPoints().addPoints(pointsToAdd);
            System.out.println(p.getName() + " has finished and received " + pointsToAdd + " points.");
        }
    }

    public static int calculatePointsForPlacement() {
        switch (placements.size()) {
            case 3:
                return 150;
            case 2:
                return 125;
            case 1:
                return 110;
            default:
                return 100;
        }
    }





    public static void checkForEats(Player p, List<Player> players) {
        //for (Player player : players) {
            for (Object token : p.getTokens()) {
                Token tokenObj = (Token) token;
                if(tokenObj.getPosition()==null || tokenObj.getPosition()==getCells()-1){
                    continue;
                }
                for (Player otherPlayer : players) {
                    if (!otherPlayer.equals(p)) {
                        for (Object otherToken : otherPlayer.getTokens()) {
                            Token otherTokenObj = (Token) otherToken;
                            if(otherTokenObj.getPosition()==null || otherTokenObj.getPosition()==getCells()-1){
                                continue;
                            }
                            if (tokenObj.getPositionOnMap() == otherTokenObj.getPositionOnMap()) {
                                eat(p, otherPlayer, (Token) token, (Token) otherToken);
                            }
                        }
                    }
                }
            }
        //}
    }


    public static void eat(Player eater, Player eaten, Token eaterToken, Token eatenToken) {
        System.out.println(eater.getName() + " has eaten " + eaten.getName() + "'s token!");
        if(eaten.getPoints().getPoints()<35){
            eaten.getPoints().setPoints(0);
        }else{
            eaten.getPoints().losePoints(35);
        }
        eaten.reset(eatenToken);
    }


}