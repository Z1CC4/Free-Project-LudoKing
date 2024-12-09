package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.*;

public class RandomGame {

    public static final int BOARD_SIZE = 52;
    public static final int TOKENS_PER_PLAYER = RandomPlayer.TOKENS_PER_PLAYER;
    private final RandomPlayerManager playerManager;
    private final Random random;
    private final Scanner sc;

    public RandomGame() {
        playerManager = new RandomPlayerManager();
        random = new Random();
        sc = new Scanner(System.in);
    }

    public static void startGame() {
        RandomGame game = new RandomGame();
        game.initializePlayers();
        game.playGame();
    }

    public void initializePlayers() {
        System.out.println("Welcome to LudoKing!");
        int numPlayers = getValidInput(2, 4, "Enter number of players (2-4):");

        for (int i = 0; i < numPlayers; i++) {
            String name = getInput("Enter name for Player " + (i + 1) + ": ");
            boolean isAI = isAI("Is this player an AI? (yes/no): ");
            playerManager.addPlayer(name, isAI);
        }
    }

    private boolean isAI(String prompt) {
        while (true) {
            String input = getInput(prompt).toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            }
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
        }
    }

    public int getValidInput(int min, int max, String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                String line = sc.nextLine().trim();
                int input = Integer.parseInt(line);
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
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
        return playerManager.getAllPlayers().stream().allMatch(RandomPlayer::hasFinished);
    }

    private void playerTurn(RandomPlayer player) {
        System.out.println("\n" + player.getName() + "'s turn. Press Enter to roll the dice.");
        sc.nextLine();
        int diceRoll = rollDice();
        System.out.println("You rolled a " + diceRoll + "!\n");

        System.out.println("Your current token positions:");
        displayTokenPositions(player);

        if (diceRoll == 6) {
            if (player.tokensOut() < TOKENS_PER_PLAYER) {
                displayPlayerMenu(player, diceRoll);
            } else {
                System.out.println("Invalid input: All tokens are already out. You must move an existing token.");
                moveExistingToken(player, diceRoll);
            }
        } else if (player.isNoTokenOut()) {
            System.out.println("You need a 6 to take a token out.\n");
        } else {
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
            moveExistingToken(player, diceRoll);
        }
    }

    private void moveExistingToken(RandomPlayer player, int diceRoll) {
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
        System.out.println();
    }

    private void aiTurn(RandomPlayer player) {
        System.out.println(player.getName() + "'s turn (AI).");
        int diceRoll = rollDice();
        System.out.println("AI rolled a " + diceRoll + ".");

        if (player.isNoTokenOut() && diceRoll == 6) {
            System.out.println("AI rolled a 6 and took a token out.\n");
            player.takeTokenOut();
        } else {
            List<Integer> movableTokens = getMovableTokens(player);

            if (movableTokens.isEmpty()) {
                System.out.println("AI has no tokens to move yet.\n");
            } else {
                int tokenIndex = chooseBestTokenToMove(player, movableTokens);
                moveToken(player, tokenIndex, diceRoll);
            }
        }
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
        int newPosition = oldPosition + diceRoll;


        if (newPosition == BOARD_SIZE) {
            System.out.println(player.getName() + " puts one token inside!");
            player.markTokenInside(tokenIndex);

            if (player.tokensInside() == TOKENS_PER_PLAYER) {
                System.out.println("\nCongrats " + player.getName() + "! You won the game!");
                System.out.println("Game Over!");
                player.markFinished();
                System.exit(0);
            }
        } else if (newPosition > BOARD_SIZE) {
            System.out.println("Invalid move: Cannot exceed the board limit of " + BOARD_SIZE + ".");
            System.out.println("Skipping turn for token " + tokenIndex + ".");
        } else {
            System.out.println(player.getName() + " moves token " + tokenIndex + " from " + oldPosition + " to " + newPosition + ".\n");
            player.updateTokenPosition(tokenIndex, newPosition);
        }
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    public void announceResults() {
        System.out.println("\nGame Over! Here are the results:");
        playerManager.getAllPlayers().forEach(player -> {
            String status = player.hasFinished() ? "Winner" : "Not finished";
            System.out.println(player.getName() + " - " + status);
        });
    }
}
