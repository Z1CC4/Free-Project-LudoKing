package it.unibz.pp2024.LudoKing.gameLogic.config;

import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Color;
import it.unibz.pp2024.LudoKing.utils.Token;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import static it.unibz.pp2024.LudoKing.gameLogic.config.Game.displayMenu;

public class RandomGame {
    private final List<Player> playersInGame;
    private Player humanPlayer;
    @Setter
    private int numPlayers;
    private final Map<Token, Integer> tokenToPosition = new HashMap<>();
    private final Scanner sc;

    public RandomGame() {
        playersInGame = new ArrayList<>();
        sc = new Scanner(System.in);
        initializePlayers();
    }

    private void initializePlayers() {
        do {
            System.out.print("Enter the number of players (1-4): ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number between 1 and 4.");
                sc.next();
            }
            numPlayers = sc.nextInt();
        } while (numPlayers < 1 || numPlayers > 4);

        int humanPlayerIndex;
        do {
            System.out.print("Which player is the human? (Enter a number between 1 and " + numPlayers + "): ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number between 1 and " + numPlayers + ".");
                sc.next();
            }
            humanPlayerIndex = sc.nextInt();
        } while (humanPlayerIndex < 1 || humanPlayerIndex > numPlayers);

        System.out.print("Enter your name: ");
        sc.nextLine();
        String humanPlayerName = sc.nextLine();
        humanPlayer = new Player(humanPlayerName, Color.RED, 0);
        playersInGame.add(humanPlayer);

        int colorIndex = 0;
        for (int i = 1; i <= numPlayers; i++) {
            if (i == humanPlayerIndex) continue;
            Color aiColor = Color.values()[colorIndex++ % Color.values().length];
            playersInGame.add(new Player("AI Player " + i, aiColor, 0));
        }

        playersInGame.forEach(Player::setTokenColorsToPlayerColor);
    }

    public void startGame() {
        System.out.println("Welcome to Ludo King!");

        int round = 0;
        while (Game.gameFinished(playersInGame)) {
            round++;
            System.out.println("\nROUND " + round);

            for (Player player : playersInGame) {
                if (player.getHasFinished()) {
                    continue;
                }



                if (player.equals(humanPlayer)) {
                    humanTurn(player);
                } else {
                    aiTurn(player);
                }

            }
        }

        declareWinner();
    }



    private void humanTurn(Player player) {
        Scanner sc = new Scanner(System.in);
        Game.checkForEats(player, playersInGame);
        player.startTurn();
        boolean validChoice = false;

        while (!validChoice) {
            displayMenu();
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        if (!player.moveToken()) {
                            System.out.println("No valid moves. Turn ends.");
                        } else {
                            System.out.println(player.getName() + " moved a token.");
                        }
                        Game.checkFinish(player);
                        Game.checkForEats(player, playersInGame);
                        validChoice = true;
                    }
                    case 2 -> player.getPositionToken();
                    case 3 -> Game.showHistoryPoints(player);
                    case 4 -> showOpponentTokensPosition();
                    case 5 -> {
                        Chat chat = new Chat(playersInGame);
                        chat.startChat(player);
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }

        player.endTurn();
    }


    public void aiTurn(Player player) {
        player.startTurn();
        int diceRoll = rollDice();
        System.out.println(player.getName() + " rolled a " + diceRoll);

        for (Token token : player.getTokens()) {
            if (token.getPositionOnMap() == null) {
                token.setPositionOnMap(0);
            }

            if (token.canMove(diceRoll)) {
                token.moveForward(diceRoll);
                tokenToPosition.put(token, token.getPositionOnMap());
                System.out.println(player.getName() + " moved token " + token.getId());
                break;
            }
        }

        Game.checkFinish(player);
        Game.checkForEats(player, playersInGame);
        player.endTurn();
    }






    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private void declareWinner() {
        Player winner = Game.checkWinner();
        System.out.println("\nThe game is finished! Congratulations " + winner.getName() + " for winning the game!");
        Game.rankingList();
    }

    public void showOpponentTokensPosition() {
        for (Player player : playersInGame) {
            if (player.equals(humanPlayer)) {
                continue;
            }


            System.out.println("Opponent: " + player.getName());


            if (player.getTokens() == null || player.getTokens().isEmpty()) {
                System.out.println("No tokens available for this opponent.");
                continue;
            }

            System.out.println("Token ID | Position");
            System.out.println("--------------------------");

            for (Token token : player.getTokens()) {
                Integer position = tokenToPosition.getOrDefault(token, null);
                String positionString;

                if (token.isHome()) {
                    positionString = "In Home";
                } else if (position != null) {
                    positionString = String.valueOf(position);
                } else {
                    positionString = "Not Out Yet";
                }

                System.out.println("   " + token.getId() + "    | " + positionString);
            }

            System.out.println();
        }
    }


}

