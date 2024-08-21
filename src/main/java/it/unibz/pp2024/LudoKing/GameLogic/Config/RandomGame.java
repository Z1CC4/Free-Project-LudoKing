package it.unibz.pp2024.LudoKing.GameLogic.Config;

import it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.*;
import it.unibz.pp2024.LudoKing.Utils.Token;
import it.unibz.pp2024.LudoKing.GameLogic.Games.MiniGames.GuessTheWord;
import it.unibz.pp2024.LudoKing.GameLogic.Games.MiniGames.TicTacToe;
import it.unibz.pp2024.LudoKing.User.Player;
import it.unibz.pp2024.LudoKing.Utils.Color;
import it.unibz.pp2024.LudoKing.Utils.Placement;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.unibz.pp2024.LudoKing.GameLogic.Config.Game.checkWinner;

public class RandomGame {

    private static final int cells = 64;
    private static final Random rand = new Random();
    static Scanner sc = new Scanner(System.in);

    public static int getCells() {
        return cells;
    }

    public static Map<Player, Placement> playerToPlacement = new HashMap<>();
    public static List<Placement> placements = new ArrayList<>(List.of(Placement.FIRST, Placement.SECOND, Placement.THIRD, Placement.FOURTH));

    public static Map<Player, Color> playerToColor = new HashMap<>();

    public static Map<MiniGame, Integer> gameToPosition = new HashMap<>();

    public static List<Player> players = new ArrayList<>();

    public static void startGame() {
        System.out.println("Welcome to the Ludoking game.");
        System.out.println();

        List<Color> colors = new ArrayList<>(List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW));
        Collections.shuffle(colors);

        // Create the real player with random color
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Player realPlayer = new Player(name, colors.remove(rand.nextInt(colors.size())), 0);
        playerToColor.put(realPlayer, realPlayer.getColor());
        playerToPlacement.put(realPlayer, null);
        players.add(realPlayer);

        // Add random AI players
        for (int i = 0; i < 3; i++) {
            Player aiPlayer = new Player("AI Player " + (i + 1), colors.remove(rand.nextInt(colors.size())), 0);
            playerToColor.put(aiPlayer, aiPlayer.getColor());
            playerToPlacement.put(aiPlayer, null);
            players.add(aiPlayer);
        }

        realPlayer.setTokenColorsToPlayerColor();
        for (Player player : players) {
            if (!player.equals(realPlayer)) {
                player.setTokenColorsToPlayerColor();
            }
        }

        playerToColor.forEach((player, color) -> System.out.println("Player " + "\"" + player.getName() + "\"" + " is assigned the color " + color + "."));
        System.out.println();

        // Generate unique numbers for mini-game positions
        List<Integer> uniqueNumbers = IntStream.generate(() -> rand.nextInt(cells - 2) + 1)
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
        }

        int round = 0;

        while (!gameFinished(players)) {
            round++;
            System.out.println("ROUND " + round);
            System.out.println();
            for (Player player : players) {
                if (player.getHasFinished()) {
                    continue;
                }
                if (player.equals(players.get(0))) {
                    playerTurn(players.get(0)); // Assuming the first player is the real player
                } else {
                    aiTurn(player);
                }
            }
        }

        System.out.println(checkWinner().getName() + " has won the game!");
        rankingList(); // Ensure this method works without user input
    }

    private static void playerTurn(Player player) {
        player.startTurn();

        // Simulate dice roll
        int diceRoll = rand.nextInt(6) + 1;
        System.out.println(player.getName() + " rolled a " + diceRoll);

        // If no tokens are out, the player can only take a token out if they roll a 6
        if (player.isNoTokenOut()) {
            if (diceRoll == 6) {
                System.out.println("You rolled a 6! You can take a token out of the house.");
                takeTokenOut(player);  // Only take a token out if 6 is rolled
            } else {
                System.out.println("You didn't roll a 6. No valid moves this turn.");
            }
        } else {
            // If tokens are already out, handle regular movement or taking out a token if 6 is rolled
            if (diceRoll == 6) {
                System.out.println("You rolled a 6! Do you want to (1) take a token out or (2) move a token?");
                int choice = sc.nextInt();
                sc.nextLine();  // Consume newline
                if (choice == 1) {
                    takeTokenOut(player);  // Player chooses to take a token out
                } else {
                    moveToken(player);  // Player chooses to move a token
                }
            } else {
                moveToken(player);  // Move token since no 6 was rolled
            }
        }

        checkFinish(player);
        checkForEats(player, players);
        miniGame(player);
        player.endTurn();
    }
    private static void aiTurn(Player player) {
        player.startTurn();

        // Simulate dice roll
        int diceRoll = rand.nextInt(6) + 1;
        System.out.println(player.getName() + " rolled a " + diceRoll);

        // If no tokens are out, the AI can only take a token out if it rolls a 6
        if (player.isNoTokenOut()) {
            if (diceRoll == 6) {
                System.out.println(player.getName() + " rolled a 6 and can take a token out.");
                takeTokenOut(player);  // Only take a token out if 6 is rolled
            } else {
                System.out.println(player.getName() + " didn't roll a 6. No valid moves this turn.");
            }
        } else {
            // If tokens are already out, AI decides whether to take a token out (if 6) or move
            if (diceRoll == 6) {
                if (rand.nextBoolean()) {  // Randomly decide whether to take out a token or move one
                    takeTokenOut(player);  // AI chooses to take a token out
                } else {
                    moveToken(player);  // AI chooses to move a token
                }
            } else {
                moveToken(player);  // Move token since no 6 was rolled
            }
        }

        checkFinish(player);
        checkForEats(player, players);
        miniGame(player);
        player.endTurn();
    }

    private static void takeTokenOut(Player player) {
        List<Token> tokens = player.getTokens(); // Assuming getTokens() method exists
        if (!tokens.isEmpty()) {
            if (player.equals(players.get(0))) { // Check if it's the real player
                System.out.println("Choose a token to take out (1-" + tokens.size() + "):");
                for (int i = 0; i < tokens.size(); i++) {
                    System.out.println((i + 1) + ": " + tokens.get(i)); // Use toString() to display token
                }
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline
                Token tokenToTakeOut = tokens.get(choice - 1);
                System.out.println(player.getName() + " took out token " + tokenToTakeOut);
            } else {
                Token tokenToTakeOut = tokens.get(rand.nextInt(tokens.size()));
                System.out.println(player.getName() + " took out token " + tokenToTakeOut);
            }
        }
    }

    private static void moveToken(Player player) {
        List<Token> tokens = player.getTokens(); // Assuming getTokens() method exists
        if (!tokens.isEmpty()) {
            if (player.equals(players.get(0))) { // Check if it's the real player
                System.out.println("Choose a token to move (1-" + tokens.size() + "):");
                for (int i = 0; i < tokens.size(); i++) {
                    System.out.println((i + 1) + ": " + tokens.get(i)); // Use toString() to display token
                }
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline
                Token tokenToMove = tokens.get(choice - 1);
                System.out.println(player.getName() + " moved token " + tokenToMove);
            } else {
                Token tokenToMove = tokens.get(rand.nextInt(tokens.size()));
                System.out.println(player.getName() + " moved token " + tokenToMove);
            }
        }
    }

    private static boolean gameFinished(List<Player> players) {
        // Implementation to check if the game is finished
        return players.stream().allMatch(Player::getHasFinished);
    }

    private static void checkFinish(Player player) {
        // Implementation for checking if the player has finished
        int pointsToAdd = 0;
        if (player.getInHome() == 4 && !player.getHasFinished()) {
            player.setHasFinished(true);
            playerToPlacement.put(player, placements.remove(0));
            pointsToAdd = calculatePointsForPlacement();
            System.out.println(player.getPoints().getPoints());
            player.getPoints().addPoints(pointsToAdd);
            System.out.println(player.getName() + " has finished and received " + pointsToAdd + " points.");
        }
    }

    private static int calculatePointsForPlacement() {
        return switch (placements.size()) {
            case 3 -> 150;
            case 2 -> 125;
            case 1 -> 110;
            default -> 100;
        };
    }

    private static void checkForEats(Player player, List<Player> players) {
        // Implementation for checking if the player has eaten other tokens
        boolean hasEaten = false;
        for (Token token : player.getTokens()) {
            if (hasEaten) {
                break;
            }
            if (token.getPosition() == null || token.getPosition() == getCells() - 1 || token.getPosition() == 0) {
                continue;
            }
            for (Player otherPlayer : players) {
                if (!otherPlayer.equals(player) && !hasEaten) {
                    for (Token otherToken : otherPlayer.getTokens()) {
                        if (otherToken.getPosition() == null || otherToken.getPosition() == getCells() - 1) {
                            continue;
                        }
                        if (token.getPositionOnMap().equals(otherToken.getPositionOnMap())) {
                            eat(player, otherPlayer, otherToken);
                            hasEaten = true;
                        }
                    }
                }
            }
        }
    }

    private static void eat(Player eater, Player eaten, Token eatenToken) {
        System.out.println(eater.getName() + " has eaten " + eaten.getName() + "'s token!");
        if (eaten.getPoints().getPoints() < 35) {
            eaten.getPoints().setPoints(0);
        } else {
            eaten.getPoints().losePoints(35);
        }
        eaten.reset(eatenToken);
    }

    private static void miniGame(Player player) {
        // Implementation for mini-games
        List<Token> list = player.getTokens();
        for (Token token : list) {
            checkMiniGame(token, player.getTokenToPositionOnMap(), player);
        }
    }

    private static void checkMiniGame(Token token, Map<Token, Integer> tToP, Player player) {
        for (Token tt : tToP.keySet()) {
            if (token.equals(tt)) {
                for (MiniGame miniGame : gameToPosition.keySet()) {
                    if (gameToPosition.get(miniGame).equals(tToP.get(tt))) {
                        if (miniGame.play(player)) {
                            player.updateTokenPosition(tt.getId(), 1);
                            System.out.println("Your token has moved 1 position ahead.");
                        } else {
                            player.updateTokenPosition(tt.getId(), -1);
                            System.out.println("Your token has moved 1 position backwards.");
                        }
                        break;
                    }
                }
            }
        }
    }

    private static void rankingList() {
        List<Player> sortedPlayers = playerToPlacement.keySet().stream()
                .sorted(Comparator.comparingInt(p -> -p.getPoints().getPoints()))
                .collect(Collectors.toList());

        System.out.println("Ranking List:");
        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player player = sortedPlayers.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getPoints().getPoints() + " points");
        }
    }
}
