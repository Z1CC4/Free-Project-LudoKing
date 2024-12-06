package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.*;

public class RandomGame {

    public static final int BOARD_SIZE = 52;
    public static final int TOKENS_PER_PLAYER = RandomPlayer.TOKENS_PER_PLAYER;
    private final RandomPlayerManager playerManager;
    private final Map<Integer, MiniGame> gameToPosition;
    private final Map<RandomPlayer, Integer> playerToPlacement;
    private final List<Integer> placements;
    private final Random random;
    private final Scanner sc;

    public RandomGame() {
        playerManager = new RandomPlayerManager(); // Use player manager
        gameToPosition = new HashMap<>();
        playerToPlacement = new HashMap<>();
        placements = new ArrayList<>(List.of(1, 2, 3, 4));
        random = new Random();
        sc = new Scanner(System.in);
    }

    public static void startGame() {
        RandomGame game = new RandomGame();
        game.initializePlayers();
        game.playGame();
    }

    public void initializePlayers() {
        System.out.print("Welcome to LudoKing");
        System.out.print("\n");
        System.out.print("Enter number of players (2-4): ");
        int numPlayers = getValidInput(2, 4);
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String name = sc.nextLine();
            System.out.print("Is this player an AI? (yes/no): ");
            boolean isAI = sc.nextLine().equalsIgnoreCase("yes");
            playerManager.addPlayer(name, isAI); // Add players using the manager
        }
        assignMiniGamePositions();
    }

    private int getValidInput(int min, int max) {
        int input;
        while (true) {
            input = sc.nextInt();
            if (input >= min && input <= max) {
                sc.nextLine(); // Clear newline after integer input
                return input;
            }
            System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
        }
    }

    private void assignMiniGamePositions() {
        int numMiniGames = 5; // Number of mini-games on the board
        while (gameToPosition.size() < numMiniGames) {
            int position = random.nextInt(BOARD_SIZE);
            if (!gameToPosition.containsKey(position)) {
                gameToPosition.put(position, new MiniGame(0.2 + random.nextDouble() * 0.6));
            }
        }
    }

    public void playGame() {
        while (!gameFinished()) {
            Game.displayMenu();
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
        return playerToPlacement.size() == playerManager.getAllPlayers().size();
    }

    private void playerTurn(RandomPlayer player) {
        System.out.println(player.getName() + "'s turn. Press Enter to roll the dice.");
        sc.nextLine(); // Wait for user input
        int diceRoll = rollDice();
        System.out.println("You rolled a " + diceRoll + "!");

        if (player.isNoTokenOut()) {
            if (diceRoll == 6) {
                System.out.println("You rolled a 6! Choose an action:");
                System.out.println("1: Take a token out");
                System.out.println("2: Move an existing token");
                int choice = getValidInput(1, 2);
                if (choice == 1) {
                    player.takeTokenOut();
                    System.out.println("You took a token out.");
                } else {
                    moveExistingToken(player, diceRoll);
                }
            } else {
                System.out.println("You need a 6 to take a token out.");
            }
        } else {
            moveExistingToken(player, diceRoll);
        }
    }

    private void moveExistingToken(RandomPlayer player, int diceRoll) {
        System.out.println("Your current token positions: ");
        displayTokenPositions(player);

        System.out.print("Choose a token to move (0 to " + (TOKENS_PER_PLAYER - 1) + "): ");
        int tokenIndex = getValidInput(0, TOKENS_PER_PLAYER - 1);
        while (player.getTokenPosition(tokenIndex) == -1) {
            System.out.print("Invalid token index. Please choose a token that is out: ");
            tokenIndex = getValidInput(0, TOKENS_PER_PLAYER - 1);
        }
        moveToken(player, tokenIndex, diceRoll);
    }

    public void displayTokenPositions(RandomPlayer player) {
        for (int i = 0; i < TOKENS_PER_PLAYER; i++) {
            if (player.getTokenPosition(i) == -1) {
                System.out.println("Token " + i + ": In House");
            } else {
                System.out.println("Token " + i + ": Position " + player.getTokenPosition(i));
            }
        }
    }

    private void aiTurn(RandomPlayer player) {
        System.out.println(player.getName() + "'s turn (AI).");
        int diceRoll = rollDice();
        System.out.println("AI rolled a " + diceRoll + ".");

        if (player.isNoTokenOut()) {
            if (diceRoll == 6) {
                System.out.println("AI rolled a 6 and took a token out.");
                player.takeTokenOut();
            }
        } else {
            moveAI(player, diceRoll);
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
        int tokenIndex = -1;
        int maxPosition = -1;
        for (int i : movableTokens) {
            int position = player.getTokenPosition(i);
            if (position > maxPosition) {
                maxPosition = position;
                tokenIndex = i;
            }
        }
        if (maxPosition == -1) {
            tokenIndex = random.nextInt(movableTokens.size());
        }
        return tokenIndex;
    }

    private void moveToken(RandomPlayer player, int tokenIndex, int diceRoll) {
        int oldPosition = player.getTokenPosition(tokenIndex);
        int newPosition = calculateNewPosition(oldPosition, diceRoll);
        System.out.println(player.getName() + " moves token " + tokenIndex + " from " + oldPosition + " to " + newPosition + ".");

        if (gameToPosition.containsKey(newPosition)) {
            MiniGame miniGame = gameToPosition.get(newPosition);
            boolean won = miniGame.play(player);
            if (!won) {
                player.handleMiniGameLoss(tokenIndex);
            } else {
                player.updateTokenPosition(tokenIndex, newPosition);
            }
        } else {
            player.updateTokenPosition(tokenIndex, newPosition);
        }

        if (player.hasAllTokensFinished()) {
            System.out.println(player.getName() + " has finished the game!");
            player.setHasFinished(true);
            assignPlacement(player);
        }
    }

    private void assignPlacement(RandomPlayer player) {
        playerToPlacement.put(player, placements.remove(0));
    }

    private int calculateNewPosition(int position, int diceRoll) {
        return (position + diceRoll) % BOARD_SIZE;
    }

    private int rollDice() {
        return random.nextInt(6) + 1;
    }

    private void announceResults() {
        System.out.println("Game over! Here are the results:");
        playerToPlacement.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getValue() + " place: " + entry.getKey().getName()));
    }

    public static class MiniGame {
        private final double difficulty;

        public MiniGame(double difficulty) {
            this.difficulty = difficulty;
        }

        public boolean play(RandomPlayer player) {
            return Math.random() < difficulty;
        }
    }
}
