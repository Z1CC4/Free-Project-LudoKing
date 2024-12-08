package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.*;

public class RandomGame {

    public static final int BOARD_SIZE = 52;
    public static final int TOKENS_PER_PLAYER = RandomPlayer.TOKENS_PER_PLAYER;
    private final RandomPlayerManager playerManager;
    private final Map<RandomPlayer, Integer> playerToPlacement;
    private final Random random;
    private final Scanner sc;

    public RandomGame() {
        playerManager = new RandomPlayerManager();
        playerToPlacement = new HashMap<>();
        random = new Random();
        sc = new Scanner(System.in);
    }

    public static void startGame() {
        RandomGame game = new RandomGame();
        game.initializePlayers();
        game.playGame();
    }

    public void initializePlayers() {
        System.out.println("\nWelcome to LudoKing!");
        int numPlayers = getValidInput(2, 4, "Enter number of players (2-4): ");
        for (int i = 0; i < numPlayers; i++) {
            String name = getInput("Enter name for Player " + (i + 1) + ": ");
            boolean isAI = getInput("Is this player an AI? (yes/no): ").equalsIgnoreCase("yes");
            playerManager.addPlayer(name, isAI);
        }
    }

    public int getValidInput(int min, int max, String prompt) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = sc.nextInt();
                sc.nextLine(); // This is necessary to consume the newline character after nextInt()
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Clear invalid input
            }
        }
    }


    private String getInput(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine(); // No need for extra newline consumption here, as nextLine handles the input correctly
        return input;
    }

    public void playGame() {
        while (!gameFinished()) {
            for (RandomPlayer player : playerManager.getAllPlayers()) {
                if (!player.hasFinished()) {
                    if (player.isAI()) {
                        aiTurn(player);
                    } else {
                        playerTurn(player);
                    }
                }
            }
        }
        announceResults();
    }

    private boolean gameFinished() {
        // The game ends when all players have finished
        return playerManager.getAllPlayers().stream().allMatch(RandomPlayer::hasFinished);
    }

    private void playerTurn(RandomPlayer player) {
        System.out.println("\n" + player.getName() + "'s turn. Press Enter to roll the dice.");
        sc.nextLine(); // Consume any leftover newline
        int diceRoll = rollDice();
        System.out.println("You rolled a " + diceRoll + "!\n");

        // Display token positions once at the start of the turn
        System.out.println("Your current token positions:");
        displayTokenPositions(player);

        if (diceRoll == 6) {
            // Prompt for action when rolling a 6
            displayPlayerMenu(player, diceRoll);
        } else if (player.isNoTokenOut()) {
            // If no tokens are out and the roll is not 6
            System.out.println("You need a 6 to take a token out.\n");
        } else {
            // Allow player to move a token if tokens are out
            moveExistingToken(player, diceRoll);
        }
    }

    private void displayPlayerMenu(RandomPlayer player, int diceRoll) {
        System.out.println("You rolled a 6! Choose an action:");
        System.out.println("1: Take a token out");
        System.out.println("2: Move an existing token");

        int choice = getValidInput(1, 2, "Your choice: ");
        if (choice == 1) {
            player.takeTokenOut();
            System.out.println("You took a token out.\n");
        } else {
            // If player wants to move a token, choose a token and move it
            moveExistingToken(player, diceRoll);
        }
    }

    private void moveExistingToken(RandomPlayer player, int diceRoll) {
        // Display positions again here to assist the decision, if needed
        System.out.println("Choose a token to move:");
        for (int i = 0; i < TOKENS_PER_PLAYER; i++) {
            String position = player.getTokenPosition(i) == -1 ? "In House" : "Position " + player.getTokenPosition(i);
            System.out.println("Token " + i + ": " + position);
        }

        int tokenIndex = getValidInput(0, TOKENS_PER_PLAYER - 1, "Choose a token to move (0 to " + (TOKENS_PER_PLAYER - 1) + "): ");
        while (player.getTokenPosition(tokenIndex) == -1) {
            tokenIndex = getValidInput(0, TOKENS_PER_PLAYER - 1, "Invalid token index. Please choose a token that is out: ");
        }
        moveToken(player, tokenIndex, diceRoll);
    }

    public void displayTokenPositions(RandomPlayer player) {
        for (int i = 0; i < TOKENS_PER_PLAYER; i++) {
            String position = player.getTokenPosition(i) == -1 ? "In House" : "Position " + player.getTokenPosition(i);
            System.out.println("Token " + i + ": " + position);
        }
        System.out.println(); // Add space after displaying token positions
    }

    private void aiTurn(RandomPlayer player) {
        System.out.println(player.getName() + "'s turn (AI).");
        int diceRoll = rollDice();
        System.out.println("AI rolled a " + diceRoll + ".");

        // If no token is out and AI rolls a 6, it should take a token out
        if (player.isNoTokenOut() && diceRoll == 6) {
            System.out.println("AI rolled a 6 and took a token out.\n");
            player.takeTokenOut();
        } else {
            // If AI has tokens out, it can move one of them
            List<Integer> movableTokens = getMovableTokens(player);

            if (movableTokens.isEmpty()) {
                System.out.println("AI has no tokens to move yet.\n");
            } else {
                int tokenIndex = chooseBestTokenToMove(player, movableTokens);
                moveToken(player, tokenIndex, diceRoll);
            }
        }
    }

    private void moveAI(RandomPlayer player, int diceRoll) {
        List<Integer> movableTokens = getMovableTokens(player);
        int tokenIndex = chooseBestTokenToMove(player, movableTokens);
        moveToken(player, tokenIndex, diceRoll);
    }

    private List<Integer> getMovableTokens(RandomPlayer player) {
        List<Integer> movableTokens = new ArrayList<>();
        for (int i = 0; i < TOKENS_PER_PLAYER; i++) {
            if (player.getTokenPosition(i) != -1) {
                movableTokens.add(i);
            }
        }
        return movableTokens;
    }

    private int chooseBestTokenToMove(RandomPlayer player, List<Integer> movableTokens) {
        return movableTokens.stream()
                .max(Comparator.comparingInt(player::getTokenPosition))
                .orElse(random.nextInt(movableTokens.size()));
    }

    private void moveToken(RandomPlayer player, int tokenIndex, int diceRoll) {
        int oldPosition = player.getTokenPosition(tokenIndex);
        int newPosition = calculateNewPosition(oldPosition, diceRoll);
        System.out.println(player.getName() + " moves token " + tokenIndex + " from " + oldPosition + " to " + newPosition + ".\n");

        player.updateTokenPosition(tokenIndex, newPosition);
    }

    private int calculateNewPosition(int currentPosition, int diceRoll) {
        return (currentPosition + diceRoll) % BOARD_SIZE;
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    public void announceResults() {
        System.out.println("\nGame Over! Here are the results:");
        playerManager.getAllPlayers().forEach(player -> {
            String status = player.hasFinished() ? "Finished" : "Not finished";
            System.out.println(player.getName() + " - " + status);
        });
    }
}