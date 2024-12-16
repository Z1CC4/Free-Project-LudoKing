package it.unibz.pp2024.LudoKing.gameLogic.config;

import it.unibz.pp2024.LudoKing.gameLogic.games.miniGames.NewGTW;
import it.unibz.pp2024.LudoKing.gameLogic.games.miniGames.TicTacToe;
import it.unibz.pp2024.LudoKing.gameLogic.games.quiz.*;
import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Color;
import it.unibz.pp2024.LudoKing.utils.Token;
import lombok.Getter;

import java.util.*;
import java.util.stream.IntStream;

public class Game {

    @Getter
    private static final int cells = 64;
    static final int numPlayers = 4;

    public static Map<Player, Color> playerToColor = new HashMap<>();
    public static Map<MiniGame, Integer> gameToPosition = new HashMap<>();
    public static List<Player> playersInGame = new ArrayList<>();

    public static void ludoKing() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Welcome to the Ludo king game.");
        System.out.println();

        List<Color> colors = new ArrayList<>(List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW));
        Collections.shuffle(colors);

        for (int i = 1; i <= numPlayers; i++) {
            Player p;
            System.out.println("Choose a name for player " + i + ":");
            String name = sc.next();
            System.out.println();
            p = new Player(name, colors.remove(rand.nextInt(colors.size())), 0);
            playerToColor.put(p, p.getColor());
            playersInGame.add(p);
            p.setTokenColorsToPlayerColor();
        }

        playerToColor.forEach((player, color) ->
                System.out.println("Player \"" + player.getName() + "\" is assigned the color " + color + "."));
        System.out.println();

        List<Integer> uniqueNumbers = IntStream.generate(() -> rand.nextInt(cells - 2) + 1)
                .distinct()
                .limit(12)
                .boxed()
                .toList();

        List<MiniGame> miniGames = Arrays.asList(
                new Quiz1(), new Quiz2(), new Quiz3(), new Quiz4(),
                new Quiz5(), new Quiz6(), new Quiz7(), new Quiz8(),
                new Quiz9(), new Quiz10(), new TicTacToe(), new NewGTW()
        );

        for (int i = 0; i < miniGames.size(); i++) {
            gameToPosition.put(miniGames.get(i), uniqueNumbers.get(i));
        }

        int round = 0;

        while (!gameFinished(playersInGame)) {
            round++;
            System.out.println("ROUND " + round);
            System.out.println();
            for (Player p : playersInGame) {
                if (!p.getHasFinished()) {
                    menu(p); // Chat can only be accessed here through menu options.
                }
            }
        }

        System.out.println("The game is finished. " + checkWinner().getName() + " has won the game!");
        System.out.println();
        rankingList();
    }


    public static boolean gameFinished(List<Player> players) {
        return !players.stream()
                .allMatch(Player::getHasFinished);
    }

    public static Player checkWinner() {
        return playerToColor.keySet().stream()
                .max(Comparator.comparingInt(p -> p.getPoints().getPoints()))
                .orElse(null);
    }

    public static void miniGame(Player p) {
        List<Token> list = p.getTokens();
        for (Token t : list) {
            checkMiniGame(t, p.getTokenToPositionOnMap(), p);
        }
    }

    public static void checkMiniGame(Token t, Map<Token, Integer> tToP, Player p) {
        for (Token tt : tToP.keySet()) {
            if (t == tt) {
                for (MiniGame m : gameToPosition.keySet()) {
                    if (gameToPosition.get(m).equals(tToP.get(tt))) {
                        if (m.play(p)) {
                            p.updateTokenPosition(tt.getId(), 1);
                            System.out.println("Your token has moved 1 position ahead.");
                        } else {
                            p.updateTokenPosition(tt.getId(), -1);
                            System.out.println("Your token has moved 1 position backwards.");
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void displayMenu() {
        System.out.println("Select your choice (Enter the number):");
        System.out.println("1. Roll the dice. (Your turn ends)");
        System.out.println("2. Get position of a specific token. (Your turn will not end)");
        System.out.println("3. Show points history. (Your turn will not end)");
        System.out.println("4. Check token position on map of all the players. (Your turn will not end)");
        System.out.println("5. Enter the chat. (Your turn will not end)");
        System.out.print("--> ");
    }


    public static void menu(Player p) {
        p.startTurn();
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            displayMenu();
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        p.moveToken();
                        checkFinish(p);
                        checkForEats(p, playersInGame);
                        miniGame(p);
                        p.endTurn();
                        System.out.println();
                        valid = true;
                        break;
                    case 2:
                        p.getPositionToken();
                        System.out.println();
                        break;
                    case 3:
                        Game.showHistoryPoints(p);
                        System.out.println();
                        break;
                    case 4:
                        showPlayersTokenPositionMap();

                        System.out.println();
                        valid = true;
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        System.out.println();
                }
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
                System.out.println();
            }
        }

        p.endTurn(); // End turn after the player has made a valid choice
    }

    public static void showPlayersTokenPositionMap() {
        for (Player p : playerToColor.keySet()) {
            System.out.println("Name: " + p.getName() + (p.isAI() ? " (AI Player)" : ""));
            p.getPositionToken();
            System.out.println();
        }
    }




    public static void showHistoryPoints(Player p) {
        if (p.getPoints().getPointsHistory() != null) {
            p.getPoints().displayHistory();
        } else {
            System.out.println();
            System.out.println("The history of points is empty.");
            System.out.println();
        }
    }

    public static void rankingList() {
        List<Player> sortedPlayers = playerToColor.keySet().stream()
                .sorted(Comparator.comparingInt(p -> -p.getPoints().getPoints()))
                .toList();

        System.out.println("Ranking List:");
        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player player = sortedPlayers.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getPoints().getPoints() + " points");
        }
    }

    public static void checkFinish(Player p) {
        int pointsToAdd;
        if (p.getInHome() == 4 && !p.getHasFinished()) {
            p.setHasFinished(true);
            playersInGame.remove(p);
            pointsToAdd = calculatePointsForPlacement();
            p.getPoints().addPoints(pointsToAdd);
            System.out.println(p.getName() + " has finished and received " + pointsToAdd + " points.");
        }
    }

    public static int calculatePointsForPlacement() {
        return switch (playersInGame.size()) {
            case 3 -> 150;
            case 2 -> 125;
            case 1 -> 110;
            default -> 100;
        };
    }

    public static void checkForEats(Player p, List<Player> players) {

        boolean hasEaten = false;
        for (Token token : p.getTokens()) {
            if (hasEaten) {
                break;
            }
            if (token.getPosition() == null || token.getPosition() == getCells() - 1 || token.getPosition() == 0) {
                continue;
            }
            for (Player otherPlayer : players) {
                if (!otherPlayer.equals(p) && !hasEaten) {
                    for (Token otherToken : otherPlayer.getTokens()) {
                        if (otherToken.getPosition() == null || otherToken.getPosition() == getCells() - 1 || otherToken.getPosition() == 0) {
                            continue;
                        }
                        if (token.getPositionOnMap().equals(otherToken.getPositionOnMap())) {
                            eat(p, otherPlayer, otherToken);
                            hasEaten = true;
                        }
                    }
                }
            }
        }
    }

    public static void eat(Player eater, Player eaten, Token eatenToken) {
        System.out.println(eater.getName() + " has eaten " + eaten.getName() + "'s token!");
        if (eaten.getPoints().getPoints() < 35) {
            eaten.getPoints().setPoints(0);
        } else {
            eaten.getPoints().losePoints(35);
        }
        eater.getPoints().addPoints(35);
        eaten.reset(eatenToken);
    }

    /* Arlind Lacej Method needed for the chat implementation */
    private static void enterChat(Player player) {
        System.out.println(player.getName() + " has entered the chat.");
        Chat chat = new Chat(playersInGame);  // Use 'playersInGame' as the participant list
        chat.startChat(player);
        System.out.println("Chat session ended.");
    }


}


