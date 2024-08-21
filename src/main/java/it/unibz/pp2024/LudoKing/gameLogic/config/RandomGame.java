package it.unibz.pp2024.LudoKing.gameLogic.config;

import it.unibz.pp2024.LudoKing.gameLogic.games.quiz.*;
import it.unibz.pp2024.LudoKing.utils.Token;
import it.unibz.pp2024.LudoKing.gameLogic.games.miniGames.GuessTheWord;
import it.unibz.pp2024.LudoKing.gameLogic.games.miniGames.TicTacToe;
import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Color;
import it.unibz.pp2024.LudoKing.utils.Placement;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.unibz.pp2024.LudoKing.gameLogic.config.Game.checkWinner;

public class RandomGame {

    private static final int cells = 64;
    private static final Random rand = new Random();
    private static final Scanner sc = new Scanner(System.in);

    private static Map<Player, Placement> playerToPlacement = new HashMap<>();
    private static List<Placement> placements = new ArrayList<>(List.of(Placement.FIRST, Placement.SECOND, Placement.THIRD, Placement.FOURTH));
    private static Map<Player, Color> playerToColor = new HashMap<>();
    private static Map<MiniGame, Integer> gameToPosition = new HashMap<>();
    private static List<Player> players = new ArrayList<>();

    public static void startGame() {
        System.out.println("Welcome to the Ludoking game.");
        List<Color> colors = new ArrayList<>(List.of(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW));
        Collections.shuffle(colors);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Player realPlayer = new Player(name, colors.remove(rand.nextInt(colors.size())), 0);
        playerToColor.put(realPlayer, realPlayer.getColor());
        playerToPlacement.put(realPlayer, null);
        players.add(realPlayer);

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
            for (Player player : players) {
                if (player.getHasFinished()) {
                    continue;
                }
                if (player.equals(players.get(0))) {
                    playerTurn(players.get(0));
                } else {
                    aiTurn(player);
                }
            }
        }

        System.out.println(checkWinner().getName() + " has won the game!");
        rankingList();
    }

    private static void playerTurn(Player player) {
        player.startTurn();
        int diceRoll = rand.nextInt(6) + 1;
        System.out.println(player.getName() + " rolled a " + diceRoll);

        if (player.isNoTokenOut()) {
            if (diceRoll == 6) {
                System.out.println("You rolled a 6! You can take a token out of the house.");
                takeTokenOut(player);
            } else {
                System.out.println("You didn't roll a 6. No valid moves this turn.");
            }
        } else {
            moveToken(player, diceRoll);
            if (diceRoll == 6 && hasTokensInHouse(player)) {
                System.out.println("You rolled a 6! You can take a token out.");
                takeTokenOut(player);
            }
        }
        checkFinish(player);
        checkForEats(player, players);
        miniGame(player);
        player.endTurn();
    }

    private static void aiTurn(Player player) {
        player.startTurn();
        int diceRoll = rand.nextInt(6) + 1;
        System.out.println(player.getName() + " rolled a " + diceRoll);

        if (player.isNoTokenOut()) {
            if (diceRoll == 6) {
                System.out.println(player.getName() + " rolled a 6 and can take a token out.");
                takeTokenOut(player);
            } else {
                System.out.println(player.getName() + " didn't roll a 6. No valid moves this turn.");
            }
        } else {
            if (diceRoll == 6 && hasTokensInHouse(player)) {
                System.out.println(player.getName() + " rolled a 6 and can take a token out.");
                takeTokenOut(player);
            } else {
                moveToken(player, diceRoll);
            }
        }

        checkFinish(player);
        checkForEats(player, players);
        miniGame(player);
        player.endTurn();
    }

    private static boolean hasTokensInHouse(Player player) {
        return player.getTokens().stream().anyMatch(token -> token.getPosition() == null);
    }

    private static void takeTokenOut(Player player) {
        List<Token> tokens = player.getTokens();
        List<Token> tokensInHouse = tokens.stream()
                .filter(token -> token.getPosition() == null)
                .collect(Collectors.toList());

        if (!tokensInHouse.isEmpty()) {
            if (player.equals(players.get(0))) {
                System.out.println("Choose a token to take out (1-" + tokensInHouse.size() + "):");
                for (int i = 0; i < tokensInHouse.size(); i++) {
                    System.out.println((i + 1) + ": Token n." + tokensInHouse.get(i).getId());
                }

                int choice = -1;
                boolean validChoice = false;
                while (!validChoice) {
                    try {
                        choice = sc.nextInt();
                        sc.nextLine();
                        if (choice < 1 || choice > tokensInHouse.size()) {
                            System.out.println("Invalid choice. Please choose a number between 1 and " + tokensInHouse.size());
                        } else {
                            validChoice = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.next();
                    }
                }

                Token tokenToTakeOut = tokensInHouse.get(choice - 1);
                System.out.println(player.getName() + " took out token n." + tokenToTakeOut.getId());
                tokenToTakeOut.setPosition(0);
            } else {
                Token tokenToTakeOut = tokensInHouse.get(rand.nextInt(tokensInHouse.size()));
                System.out.println(player.getName() + " took out token n." + tokenToTakeOut.getId());
                tokenToTakeOut.setPosition(0);
            }
        } else {
            System.out.println("No tokens are available to take out.");
        }
    }

    private static void moveToken(Player player, int diceRoll) {
        List<Token> tokens = player.getTokens();
        if (!tokens.isEmpty()) {
            if (player.equals(players.get(0))) {
                System.out.println("Choose a token to move (1-" + tokens.size() + "):");
                for (int i = 0; i < tokens.size(); i++) {
                    Token token = tokens.get(i);
                    String positionInfo = (token.getPosition() == null) ? "In house" : "Position: " + token.getPosition();
                    System.out.println((i + 1) + ": Token " + token.getId() + " (" + positionInfo + ")");
                }
                int choice = -1;
                boolean validChoice = false;
                while (!validChoice) {
                    try {
                        choice = sc.nextInt();
                        sc.nextLine();
                        if (choice < 1 || choice > tokens.size()) {
                            System.out.println("Invalid choice. Please choose a number between 1 and " + tokens.size());
                        } else {
                            validChoice = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.next();
                    }
                }

                Token tokenToMove = tokens.get(choice - 1);
                int currentPosition = tokenToMove.getPosition() == null ? 0 : tokenToMove.getPosition();
                int newPosition = calculateNewPosition(currentPosition, diceRoll);
                tokenToMove.setPosition(newPosition);
                player.updateTokenPosition(tokenToMove.getId(), newPosition);
                System.out.println(player.getName() + " moved token " + tokenToMove.getId() + " to position " + newPosition);
            } else {
                Token tokenToMove = tokens.get(rand.nextInt(tokens.size()));
                int currentPosition = tokenToMove.getPosition() == null ? 0 : tokenToMove.getPosition();
                int newPosition = calculateNewPosition(currentPosition, diceRoll);
                tokenToMove.setPosition(newPosition);
                player.updateTokenPosition(tokenToMove.getId(), newPosition);
                System.out.println(player.getName() + " moved token " + tokenToMove.getId() + " to position " + newPosition);
            }
        } else {
            System.out.println("No tokens are available to move.");
        }
    }

    private static int calculateNewPosition(Integer currentPosition, int diceRoll) {
        return (currentPosition + diceRoll) % cells;
    }

    private static boolean gameFinished(List<Player> players) {
        return players.stream().allMatch(Player::getHasFinished);
    }

    private static void rankingList() {
        List<Map.Entry<Player, Placement>> ranking = playerToPlacement.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        System.out.println("Ranking:");
        for (Map.Entry<Player, Placement> entry : ranking) {
            System.out.println(entry.getKey().getName() + " finished in position: " + entry.getValue());
        }
    }

    private static void checkFinish(Player player) {
        long tokensFinished = player.getTokens().stream()
                .filter(token -> token.getPosition() != null && token.getPosition() == cells)
                .count();

        if (tokensFinished == 4) {
            System.out.println(player.getName() + " has finished the game!");
            player.setHasFinished(true);
            playerToPlacement.put(player, placements.remove(0));
        }
    }

    private static void checkForEats(Player currentPlayer, List<Player> allPlayers) {
        for (Player otherPlayer : allPlayers) {
            if (otherPlayer.equals(currentPlayer)) continue;

            for (Token otherPlayerToken : otherPlayer.getTokens()) {
                if (currentPlayer.getTokens().stream()
                        .anyMatch(currentPlayerToken -> Objects.equals(currentPlayerToken.getPosition(), otherPlayerToken.getPosition()))) {
                    otherPlayerToken.setPosition(null);
                    System.out.println(currentPlayer.getName() + " ate " + otherPlayer.getName() + "'s token " + otherPlayerToken.getId() + "!");
                }
            }
        }
    }

    private static void miniGame(Player player) {
        if (player.getTokens().stream().anyMatch(token -> gameToPosition.containsValue(token.getPosition()))) {
            System.out.println(player.getName() + " landed on a minigame square!");
            int randomIndex = rand.nextInt(gameToPosition.size());
            MiniGame selectedMiniGame = (MiniGame) gameToPosition.keySet().toArray()[randomIndex];
            selectedMiniGame.play(player);
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
}