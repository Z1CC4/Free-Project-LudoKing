package it.unibz.pp2024.LudoKing.gameLogic.config;

import java.util.*;

public class RandomGame {

    private static final int BOARD_SIZE = 52;
    private static final int TOKENS_PER_PLAYER = 4;
    private final List<Player> players;
    private final Map<Integer, MiniGame> gameToPosition;
    private final Map<Player, Integer> playerToPlacement;
    private final List<Integer> placements;
    private final Random random;
    private final Scanner sc;

    public RandomGame() {
        players = new ArrayList<>();
        gameToPosition = new HashMap<>();
        playerToPlacement = new HashMap<>();
        placements = new ArrayList<>(List.of(1, 2, 3, 4));
        random = new Random();
        sc = new Scanner(System.in);
    }

    public static void startGame() {
    }

    public void initializePlayers() {
        System.out.print("Enter number of players (2-4): ");
        int numPlayers = sc.nextInt();
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.print("Invalid number of players. Please enter a number between 2 and 4: ");
            numPlayers = sc.nextInt();
        }
        sc.nextLine(); // Clear newline

        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String name = sc.nextLine();
            System.out.print("Is this player an AI? (yes/no): ");
            boolean isAI = sc.nextLine().equalsIgnoreCase("yes");
            players.add(new Player(name, isAI));
        }
        assignMiniGamePositions();
    }

    private void assignMiniGamePositions() {
        int numMiniGames = 5;
        while (gameToPosition.size() < numMiniGames) {
            int position = random.nextInt(BOARD_SIZE);
            if (!gameToPosition.containsKey(position)) {
                // Create a new MiniGame with a random win probability between 0.2 and 0.8
                gameToPosition.put(position, new MiniGame(0.2 + random.nextDouble() * 0.6));
            }
        }
    }

    public void playGame() {
        while (!gameFinished()) {
            for (Player player : players) {
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
        return playerToPlacement.size() == players.size();
    }

    private void playerTurn(Player player) {
        System.out.println(player.getName() + "'s turn. Press Enter to roll the dice.");
        sc.nextLine(); // Wait for user input
        int diceRoll = rollDice();
        System.out.println("You rolled a " + diceRoll + "!");

        if (diceRoll == 6 && !player.isNoTokenOut()) {
            boolean canTakeTokenOut = false;
            for (int i = 0; i < TOKENS_PER_PLAYER; i++) {
                if (player.getTokenPosition(i) == -1) {
                    canTakeTokenOut = true;
                    break;
                }
            }
            if (canTakeTokenOut) {
                System.out.println("You rolled a 6! Choose an action:");
                System.out.println("1: Take a token out");
                System.out.println("2: Move an existing token");
                int choice = sc.nextInt();
                while (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice. Please enter 1 or 2:");
                    choice = sc.nextInt();
                }
                if (choice == 1) {
                    player.takeTokenOut();
                    System.out.println("You took a token out.");
                } else {
                    moveExistingToken(player, diceRoll);
                }
            } else {
                System.out.println("All tokens are already out. Moving an existing token.");
                moveExistingToken(player, diceRoll);
            }
        } else if (player.isNoTokenOut()) {
            if (diceRoll == 6) {
                System.out.println("You rolled a 6! Taking a token out.");
                player.takeTokenOut();
            } else {
                System.out.println("You need a 6 to take a token out.");
            }
        } else {
            moveExistingToken(player, diceRoll);
        }
    }

    private void moveExistingToken(Player player, int diceRoll) {
        System.out.print("Choose a token to move (0 to " + (TOKENS_PER_PLAYER - 1) + "): ");
        int tokenIndex = sc.nextInt();
        while (tokenIndex < 0 || tokenIndex >= TOKENS_PER_PLAYER || player.getTokenPosition(tokenIndex) == -1) {
            System.out.print("Invalid token index. Please enter a valid token number: ");
            tokenIndex = sc.nextInt();
        }
        moveToken(player, tokenIndex, diceRoll);
    }

    private void aiTurn(Player player) {
        System.out.println(player.getName() + "'s turn (AI).");
        int diceRoll = rollDice();
        System.out.println("AI rolled a " + diceRoll + ".");

        // If the player has no tokens out, AI will take a token out on a roll of 6
        if (player.isNoTokenOut()) {
            if (diceRoll == 6) {
                System.out.println("AI rolled a 6 and took a token out.");
                player.takeTokenOut();
            }
        } else {
            // If the player has tokens out, the AI decides which token to move
            List<Integer> movableTokens = new ArrayList<>();

            // Collect all tokens that are out (position > -1)
            for (int i = 0; i < TOKENS_PER_PLAYER; i++) {
                if (player.getTokenPosition(i) != -1) {
                    movableTokens.add(i);
                }
            }

            // AI chooses the token closest to finishing (maximum token position)
            int tokenIndex = -1;
            int maxPosition = -1;

            for (int i : movableTokens) {
                int position = player.getTokenPosition(i);
                if (position > maxPosition) {
                    maxPosition = position;
                    tokenIndex = i;
                }
            }

            // In case of a tie (multiple tokens at the same position), select randomly
            if (maxPosition == -1) {
                tokenIndex = random.nextInt(movableTokens.size());
            }

            moveToken(player, tokenIndex, diceRoll);
        }
    }


    private void moveToken(Player player, int tokenIndex, int diceRoll) {
        int oldPosition = player.getTokenPosition(tokenIndex);
        int newPosition = calculateNewPosition(oldPosition, diceRoll);
        System.out.println(player.getName() + " moves token " + tokenIndex + " from " + oldPosition + " to " + newPosition + ".");

        if (gameToPosition.containsKey(newPosition)) {
            System.out.println("Token landed on a MiniGame!");
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

    private void assignPlacement(Player player) {
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

    public static void main(String[] args) {
        RandomGame game = new RandomGame();
        game.initializePlayers();
        game.playGame();
    }

    static class Player {
        private final String name;
        private final boolean isAI;
        private final int[] tokens;
        private boolean hasFinished;

        public Player(String name, boolean isAI) {
            this.name = name;
            this.isAI = isAI;
            this.tokens = new int[TOKENS_PER_PLAYER];
            Arrays.fill(tokens, -1);
            this.hasFinished = false;
        }

        public String getName() {
            return name;
        }

        public boolean isAI() {
            return isAI;
        }

        public boolean hasFinished() {
            return hasFinished;
        }

        public void setHasFinished(boolean hasFinished) {
            this.hasFinished = hasFinished;
        }

        public boolean isNoTokenOut() {
            for (int token : tokens) {
                if (token != -1) return false;
            }
            return true;
        }

        public void takeTokenOut() {
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i] == -1) {
                    tokens[i] = 0;
                    return;
                }
            }
        }

        public int getTokenPosition(int index) {
            return tokens[index];
        }

        public void updateTokenPosition(int index, int position) {
            tokens[index] = position;
        }

        public boolean hasAllTokensFinished() {
            for (int token : tokens) {
                if (token != BOARD_SIZE) return false;
            }
            return true;
        }

        public void handleMiniGameLoss(int tokenIndex) {
            System.out.println("Token " + tokenIndex + " was sent back to the start.");
            tokens[tokenIndex] = 0;
        }
    }

    static class MiniGame {
        private final double winProbability;

        public MiniGame(double winProbability) {
            this.winProbability = winProbability;
        }

        public boolean play(Player player) {
            System.out.println("MiniGame played by " + player.getName() + ".");
            return Math.random() < winProbability;
        }
    }
}

