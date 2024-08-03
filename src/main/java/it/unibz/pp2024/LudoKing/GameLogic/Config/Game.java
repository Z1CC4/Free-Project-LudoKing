package it.unibz.pp2024.LudoKing.GameLogic.Config;

import it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.*;
import it.unibz.pp2024.LudoKing.MiniGames.GuessTheWord;
import it.unibz.pp2024.LudoKing.MiniGames.TicTacToe;
import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.User.Points;
import it.unibz.pp2024.LudoKing.Utils.Color;
import it.unibz.pp2024.LudoKing.GameLogic.Utils.Token;
import it.unibz.pp2024.LudoKing.Utils.Placement;

import javax.net.ssl.SSLContext;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;




public class Game {


    private static final int cells=64;


    public static int getCells(){
        return cells;
    }

    public static Map<Player, Placement> playerToPlacement=new HashMap<>();
    public static List<Placement> placements=new ArrayList<>(List.of(Placement.FIRST, Placement.SECOND, Placement.THIRD, Placement.FOURTH));

    public static Map<Player, Color> playerToColor = new HashMap<>();

    public static Map<MiniGame, Integer> gameToPosition=new HashMap<>();

    public static void ludoKing() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Welcome to the Ludoking game.");

        List<Color> colors = new ArrayList<>(List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW));
        Collections.shuffle(colors);



        System.out.print("Choose a name:");
        String name = sc.next();
        Player p1 = new Player(name, colors.remove(rand.nextInt(0, colors.size())), 0);
        playerToColor.put(p1, p1.getColor());
        playerToPlacement.put(p1, null);


        //Player p2=new Bot("Bot 1",colors.remove(rand.nextInt(0,colors.size())), 0);
        //Player p3=new Bot("Bot 2",colors.remove(rand.nextInt(0,colors.size())), 0);
        //Player p4=new Bot("Bot 3",colors.remove(rand.nextInt(0,colors.size())), 0);
        //playerToColor.put(p2, p2.getColor());
        //playerToPlacement.put(p2, null);
        //playerToColor.put(p3, p3.getColor());
        // playerToPlacement.put(p3, null);
        //playerToColor.put(p4, p4.getColor());
        //playerToPlacement.put(p4, null);

        List<Player> players = playerToColor.keySet().stream()
                .collect(Collectors.toList());

        p1.setTokenColorsToPlayerColor();
        //p2.setTokenColorsToPlayerColor();
        //p3.setTokenColorsToPlayerColor();
        //p4.setTokenColorsToPlayerColor();

        playerToColor.forEach((player, color) -> System.out.println(player.getName() + " is assigned the color " + color));
        System.out.println();

        int[] board=new int[cells];

        List<Integer> uniqueNumbers = IntStream.generate(() -> rand.nextInt(cells))
                .distinct()
                .limit(12)
                .boxed()
                .collect(Collectors.toList());

        //List<Integer> startingPos=new ArrayList<>(List.of(0,16,32,48));
        //Collections.shuffle(startingPos);

        /*for (Player p : players) {
            p.setStartingPositions(new ArrayList<>(startingPos)); // Pass a copy of startingPos
        }*/

        /*for(Player p:playerToColor.keySet()){
            List<Token> list=p.getTokens();
            for(Token t:list){
                p.addEntriesTokToPosMap(t, startingPos.get(0));
            }
            startingPos.remove(0);
        }*/

        /*MiniGame m1=new Quiz1();
        MiniGame m2=new Quiz2();
        MiniGame m3=new Quiz3();
        MiniGame m4=new Quiz4();
        MiniGame m5=new Quiz5();
        MiniGame m6=new Quiz6();
        MiniGame m7=new Quiz7();
        MiniGame m8=new Quiz8();
        MiniGame m9=new Quiz9();
        MiniGame m10=new Quiz10();
        MiniGame m11=new TicTacToe();
        MiniGame m12=new GuessTheWord();

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

        gameToPosition.put(m1, miniGameOne);
        gameToPosition.put(m2, miniGameTwo);
        gameToPosition.put(m3, miniGameThree);
        gameToPosition.put(m4, miniGameFour);
        gameToPosition.put(m5, miniGameFive);
        gameToPosition.put(m6, miniGameSix);
        gameToPosition.put(m7, miniGameSeven);
        gameToPosition.put(m8, miniGameEight);
        gameToPosition.put(m9, miniGameNine);
        gameToPosition.put(m10, miniGameTen);
        gameToPosition.put(m11, miniGameEleven);
        gameToPosition.put(m12, miniGameTwelve);*/

        List<MiniGame> miniGames = Arrays.asList(
                new Quiz1(), new Quiz2(), new Quiz3(), new Quiz4(),
                new Quiz5(), new Quiz6(), new Quiz7(), new Quiz8(),
                new Quiz9(), new Quiz10(), new TicTacToe(), new GuessTheWord()
        );

        for (int i = 0; i < miniGames.size(); i++) {
            gameToPosition.put(miniGames.get(i), uniqueNumbers.get(i));
        }

        while (!gameFinished(players)) {
            for(Player p:playerToColor.keySet()){
                if(p.getHasFinished()){
                    continue;
                }
                //p.startTurn();
                menu(p);
                //checkFinish(p);
                //p.endTurn();
            }
        }

        System.out.println(checkWinner().getName() + " has won!");


    }


    public static boolean gameFinished(List<Player> players) {
        boolean allFinished = players.stream()
                .allMatch(Player::getHasFinished);
        return allFinished;
    }

    public static Player checkWinner() {
        return playerToPlacement.keySet().stream()
                .max(Comparator.comparingInt(p -> p.getPoints().listPoints()))
                .orElse(null);
    }


    public static void miniGame(Player p) {
        List<Token> list=p.getTokens();
        for(Token t:list){
            checkMiniGame(t, p.getTokenToPositionOnMap());
        }
    }

    public static void checkMiniGame(Token t, Map<Token, Integer> tToP){
        for(Token tt:tToP.keySet()){
            if(t==tt){
                for(MiniGame m:gameToPosition.keySet()){
                    if(gameToPosition.get(m)==tToP.get(tt)){
                        m.play();
                        break;
                    }
                }
            }

        }
    }



    public static void menu(Player p){
        p.startTurn();
        Scanner sc=new Scanner(System.in);
        boolean valid=false;
        while(!valid){
            System.out.println("Select your choice(Enter the number).");
            System.out.println("1.Roll the dice. (Your turn ends)");
            System.out.println("2.Get position of a specific token. (Your turn will not end)");
            System.out.println("3.Show points history. (Your turn will not end)");
            System.out.println("4.Show ranking chart. (Your turn will not end)");
            System.out.print("-->");
            switch(sc.nextInt()){
                case 1:
                    p.moveToken();
                    checkFinish(p);
                    miniGame(p);
                    p.endTurn();
                    System.out.println();
                    valid=true;
                    break;
                case 2:
                    p.getPositionToken();
                    System.out.println();
                    break;
                case 4:
                    rankingList();
                    System.out.println();
                    break;
                case 3:
                    if(p.getPoints().getPointsHistory()!=null){
                        p.getPoints().displayHistory();
                    }else{
                        System.out.println();
                        System.out.println("The history of points is empty.");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice. Insert one of the number on the screen..");
                    System.out.println();
            }
        }

    }


    public static void rankingList() {
        List<Player> sortedPlayers = playerToPlacement.keySet().stream()
                .sorted(Comparator.comparingInt(p -> -p.getPoints().listPoints()))
                .collect(Collectors.toList());

        System.out.println("Ranking List:");
        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player player = sortedPlayers.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getPoints().listPoints() + " points");
        }
    }


    /*public void checkFinish(Player p){
        checkF1(p);
        checkF2(p);
        checkF3(p);
        checkF4(p);
    }

    public boolean checkF1(Player p){
        Points points=p.getPoints();
        //int tot=points.
        if(p.getInHome()==4 && !p.getHasFinished()){
            p.setHasFinished(true);
            points.addPoints(150);
            p.setPoints(points);
            System.out.println(p.getName()+" has finished.");
            playerToPlacement.put(p, placements.remove(0));
            return true;
        }
        return false;
    }

    public boolean checkF2(Player p){
        Points points=p.getPoints();
        if(!checkF1(p)){
            if(p.getInHome()==4 && !p.getHasFinished()){
                p.setHasFinished(true);
                points.addPoints(125);
                p.setPoints(points);
                System.out.println(p.getName()+" has finished.");
                playerToPlacement.put(p, placements.remove(0));
                return true;
            }
        }
        return false;
    }

    public boolean checkF3(Player p){
        Points points=p.getPoints();
        if(!checkF1(p) && !checkF2(p)){
            if(p.getInHome()==4 && !p.getHasFinished()){
                p.setHasFinished(true);
                points.addPoints(110);
                p.setPoints(points);
                System.out.println(p.getName()+" has finished.");
                playerToPlacement.put(p, placements.remove(0));
                return true;
            }
        }
        return false;
    }

    public boolean checkF4(Player p){
        Points points=p.getPoints();
        if(!checkF1(p) && !checkF2(p) && !checkF3(p)){
            if(p.getInHome()==4 && !p.getHasFinished()){
                p.setHasFinished(true);
                points.addPoints(125);
                p.setPoints(points);
                System.out.println(p.getName()+" has finished.");
                playerToPlacement.put(p, placements.remove(0));
                return true;
            }
        }
        return false;
    }*/

    public static void checkFinish(Player p) {
        if (p.getInHome() == 4 && !p.getHasFinished()) {
            p.setHasFinished(true);
            int pointsToAdd = calculatePointsForPlacement();
            p.getPoints().addPoints(pointsToAdd);
            System.out.println(p.getName() + " has finished and received " + pointsToAdd + " points.");
            playerToPlacement.put(p, placements.remove(0));
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





    public static void checkForEats(List<Player> players) {
        for (Player player : players) {
            for (Object token : player.getTokens()) {
                Token tokenObj = (Token) token;
                for (Player otherPlayer : players) {
                    if (!otherPlayer.equals(player)) {
                        for (Object otherToken : otherPlayer.getTokens()) {
                            Token otherTokenObj = (Token) otherToken;
                            if (tokenObj.getPosition() == otherTokenObj.getPosition()) {
                                eat(player, otherPlayer, (Token) token, (Token) otherToken);
                            }
                        }
                    }
                }
            }
        }
    }


    public static void eat(Player eater, Player eaten, Token eaterToken, Token eatenToken) {
        System.out.println(eater.getName() + " has eaten " + eaten.getName() + "'s token!");
        eaten.getPoints().losePoints(35);
        eaten.reset(eatenToken);
    }


}
