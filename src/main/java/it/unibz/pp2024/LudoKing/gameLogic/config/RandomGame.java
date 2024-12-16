package it.unibz.pp2024.LudoKing.gameLogic.config;

import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Color;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static it.unibz.pp2024.LudoKing.gameLogic.config.Game.displayMenu;

public class RandomGame {
    private final List<Player> playersInGame;
    private Player humanPlayer;
    @Setter
    private int numPlayers;
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
                    aiTurn(player);  // Example with medium AI
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
                        validChoice = true; // Turn ends
                    }
                    case 2 -> player.getPositionToken();
                    case 3 -> Game.showHistoryPoints(player);
                    case 4 -> Game.showPlayersTokenPositionMap();
                    case 5 -> {
                        Chat chat = new Chat(playersInGame); // Initialize chat with all players
                        chat.startChat(player); // Start chat for the human player
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
        Game.checkForEats(player, playersInGame);
        player.startTurn();
        int diceRoll = rollDice();
        System.out.println(player.getName() + " rolled a " + diceRoll);


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
        Game.rankingList(); // Display rankings at the end
    }




    private void showPlayersTokenPositionMap() {
        System.out.println("\nPlayer Token Positions on the Map:");
        for (Player player : playersInGame) {
            System.out.println(player.getName() + ":");
            player.displayTokenPositionOnMap();
        }
    }
}

