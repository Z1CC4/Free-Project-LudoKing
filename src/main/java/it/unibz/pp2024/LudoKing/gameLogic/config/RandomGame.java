package it.unibz.pp2024.LudoKing.gameLogic.config;

import it.unibz.pp2024.LudoKing.user.Player;
import it.unibz.pp2024.LudoKing.utils.Color;
import it.unibz.pp2024.LudoKing.utils.Token;
import java.util.*;

public class RandomGame {

    private static List<Player> playersInGame;
    private static Player humanPlayer;

    public RandomGame(String humanPlayerName, int numPlayers) {
        // Initialize players in the game
        playersInGame = new ArrayList<>();

        // Create human player
        humanPlayer = new Player(humanPlayerName, Color.RED, 0, true); // Human player is red
        List<Player> aiPlayers = new ArrayList<>();

        // Adding AI players
        for (int i = 0; i < numPlayers - 1; i++) {
            aiPlayers.add(new Player("AI Player " + (i + 1), Color.values()[(i + 1) % 4], 0, false));
        }

        // Add all players to the main list
        playersInGame.add(humanPlayer);
        playersInGame.addAll(aiPlayers);

        // Assign token colors and positions
        playersInGame.forEach(Player::setTokenColorsToPlayerColor);
    }

    public void startGame() {
        System.out.println("Welcome to Ludo King!");

        int round = 0;
        while (!Game.gameFinished(playersInGame)) {
            round++;
            System.out.println("ROUND " + round);

            for (Player p : playersInGame) {
                if (p.getHasFinished()) {
                    continue; // Skip players who have finished
                }

                if (p.equals(humanPlayer)) {
                    // Human player's turn
                    humanTurn(p);
                } else {
                    // AI players' turn
                    aiTurn(p);  // AI turns are handled without input prompts
                }
            }
        }

        declareWinner();
    }

    public static void eat(Player eater, Player eaten, Token eatenToken) {
        Game.eat(eater, eaten, eatenToken);  // Reset the eaten token
    }


    private void humanTurn(Player p) {
        p.startTurn();
        Scanner sc = new Scanner(System.in);
        boolean validChoice = false;

        while (!validChoice) {
            displayMenu();
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                System.out.println();
                switch (choice) {
                    case 1:
                        p.moveToken();
                        Game.checkFinish(p);
                        Game.checkForEats(p, playersInGame);
                        Game.miniGame(p);
                        p.endTurn();
                        validChoice = true;
                        break;
                    case 2:
                        p.getPositionToken();
                        break;
                    case 3:
                        Game.showHistoryPoints(p);
                        break;
                    case 4:
                        Game.rankingList();
                        break;
                    case 5:
                        showPlayersTokenPositionMap();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
        }
    }




    private void showPlayersTokenPositionMap() {
        System.out.println("Player Token Positions on the Map:");
        for (Player player : playersInGame) {
            System.out.println(player.getName() + ":");
            player.displayTokenPositionOnMap();  // This will print the positions for each player
        }
    }

    private void checkForEat(Player eater, Player eaten) {
        for (Token token : eater.getTokens()) {
            if (token.getPosition() != null) {  // If the token is out
                for (Token opponentToken : eaten.getTokens()) {
                    if (opponentToken.getPosition() != null && token.getPosition().equals(opponentToken.getPosition())) {
                        // If tokens land on the same position, trigger the eating mechanism
                        eat(eater, eaten, opponentToken);
                        return;  // Exit after eating
                    }
                }
            }
        }
    }


    private void aiTurn(Player p) {
        p.startTurn();
        int diceRoll = rollDice();  // AI rolls the dice
        System.out.println(p.getName() + " rolls: " + diceRoll);

        Token tokenToMove = p.chooseRandomToken();  // Automatically choose a random token
        if (tokenToMove == null) {
            // If no token is out, the AI takes a token out
            p.takeTokenOut();
        } else {
            // Otherwise, AI moves the chosen token
            p.moveToken();
        }

        // After moving the token, check if any tokens were eaten
        for (Player opponent : playersInGame) {
            if (!opponent.equals(p)) {
                checkForEat(p, opponent);  // Check if AI's token has eaten an opponent's token
            }
        }

        Game.checkFinish(p);  // Check if the game has been finished
        Game.checkForEats(p, playersInGame);  // Check for any token eats in general
        Game.miniGame(p);  // Handle mini-games or additional logic if any

        if (!p.getHasFinished() && diceRoll == 6) {
            System.out.println(p.getName() + " rolled a 6 and moved a token.");
        }

        p.endTurn();  // End AI's turn
    }

    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;  // Return a value between 1 and 6
    }

    private void declareWinner() {
        Player winner = Game.checkWinner();
        System.out.println("The game is finished. " + winner.getName() + " has won the game!");
    }

    private void displayMenu() {
        System.out.println("Select your choice (Enter the number):");
        System.out.println("1. Roll the dice. (Your turn ends)");
        System.out.println("2. Get position of a specific token. (Your turn will not end)");
        System.out.println("3. Show points history. (Your turn will not end)");
        System.out.println("5. Check token position on map of all the players. (Your turn will not end)");

        System.out.print("-->");
    }
}