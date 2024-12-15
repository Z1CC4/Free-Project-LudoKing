package it.unibz.pp2024.LudoKing.gameLogic.config;

import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Color;
import it.unibz.pp2024.LudoKing.utils.Token;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomGame {
    private final List<Player> playersInGame;
    private Player humanPlayer;
    @Setter
    private int numPlayers;
    private final Scanner sc;  // Reuse the same Scanner instance

    public RandomGame() {
        playersInGame = new ArrayList<>();
        sc = new Scanner(System.in); // Initialize the scanner once
        initializePlayers();
    }

    private void initializePlayers() {
        // Prompt for number of players
        do {
            System.out.print("Enter the number of players (1-4): ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number between 1 and 4.");
                sc.next();
            }
            numPlayers = sc.nextInt();
        } while (numPlayers < 1 || numPlayers > 4);

        // Prompt for the human player's position (no input needed for AI)
        int humanPlayerIndex;
        do {
            System.out.print("Which player is the human? (Enter a number between 1 and " + numPlayers + "): ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number between 1 and " + numPlayers + ".");
                sc.next();
            }
            humanPlayerIndex = sc.nextInt();
        } while (humanPlayerIndex < 1 || humanPlayerIndex > numPlayers);

        // Assign human player details
        System.out.print("Enter your name: ");
        sc.nextLine(); // Consume leftover newline
        String humanPlayerName = sc.nextLine();
        humanPlayer = new Player(humanPlayerName, Color.RED, 0, true); // Assuming RED for human player
        playersInGame.add(humanPlayer);

        // Add AI players (automatically assigned without any input from the user)
        int colorIndex = 0;
        for (int i = 1; i <= numPlayers; i++) {
            if (i == humanPlayerIndex) continue;  // Skip the human player

            Color aiColor = Color.values()[colorIndex++ % Color.values().length];  // Cycle through colors
            playersInGame.add(new Player("AI Player " + i, aiColor, 0, true));  // AI player with color and no input required
        }

        // Assign token colors and positions
        playersInGame.forEach(Player::setTokenColorsToPlayerColor);
    }

    public void startGame() {
        System.out.println("Welcome to Ludo King!");

        int round = 0;
        while (!Game.gameFinished(playersInGame)) {
            round++;
            System.out.println("\nROUND " + round);

            for (Player player : playersInGame) {
                if (player.getHasFinished()) {
                    continue; // Skip players who have finished
                }



                if (player.equals(humanPlayer)) {
                    humanTurn(player); // Human player logic
                } else {
                    aiTurn(player); // AI player logic
                }

            }
        }

        declareWinner(); // Declare the winner at the end
    }

    private void humanTurn(Player player) {
        player.startTurn();
        boolean validChoice = false;

        while (!validChoice) {
            displayMenu(); // Show menu options
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        int diceRoll = rollDice();

                        if (!player.moveToken(diceRoll)) {
                            System.out.println("No valid moves. Turn ends.");
                        } else {
                            System .out.println(player.getName() + " moved a token.");
                        }
                        Game.checkFinish(player);
                        Game.checkForEats(player, playersInGame);
                        validChoice = true; // Turn ends
                    }
                    case 2 -> player.getPositionToken(); // Show specific token's position
                    case 3 -> Game.showHistoryPoints(player); // Show player's points history
                    case 5 -> showPlayersTokenPositionMap(); // Show map of all players' tokens
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                sc.next(); // Consume invalid input
            }
        }

        player.endTurn();
    }

    public void aiTurn(Player player) {
        player.startTurn();
        int diceRoll = rollDice();
        System.out.println(player.getName() + " rolled a " + diceRoll);

        // If the AI player rolls a 6 and has tokens in the start area, move one out
        if (diceRoll == 6) {
            if (hasTokenInStart(player)) {
                moveTokenOut(player);
            } else {
                // Otherwise, move the token forward
                player.autoSelectToken(diceRoll); // Automatically select a token to move
            }
        } else {
            // For any other roll, select and move a valid token
            player.autoSelectToken(diceRoll);
        }

        Game.checkFinish(player);
        Game.checkForEats(player, playersInGame);

        player.endTurn();
    }

    private boolean hasTokenInStart(Player player) {
        // Check if any token of the player is still in the start area
        for (Token token : player.getTokens()) {
            if (token.isInStart()) {
                return true;
            }
        }
        return false;
    }

    private void moveTokenOut(Player player) {
        // Automatically move one of the player's tokens out from the start area
        for (Token token : player.getTokens()) {
            if (token.isInStart()) {
                token.moveOut();
                System.out.println(player.getName() + " moved Token " + token.getId() + " out of the start area.");
                return; // Stop after moving one token out
            }
        }
    }




    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Return a value between 1 and 6
    }

    private void declareWinner() {
        Player winner = Game.checkWinner();
        System.out.println("\nThe game is finished! Congratulations " + winner.getName() + " for winning the game!");
        Game.rankingList(); // Display rankings at the end
    }

    private void displayMenu() {
        System.out.println("\nSelect your choice (Enter the number):");
        System.out.println("1. Roll the dice. (Your turn ends)");
        System.out.println("2. Get position of a specific token. (Your turn will not end)");
        System.out.println("3. Show points history. (Your turn will not end)");
        System.out.println("5. Check token position on map of all the players. (Your turn will not end)");
        System.out.print("--> ");
    }

    private void showPlayersTokenPositionMap() {
        System.out.println("\nPlayer Token Positions on the Map:");
        for (Player player : playersInGame) {
            System.out.println(player.getName() + ":");
            player.displayTokenPositionOnMap();
        }
    }
}

