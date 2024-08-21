package it.unibz.pp2024.LudoKing.GameLogic.Games.MiniGames;

import java.util.Random;
import java.util.Scanner;

import it.unibz.pp2024.LudoKing.GameLogic.Games.Quiz.MiniGame;
import it.unibz.pp2024.LudoKing.User.Player;

public class TicTacToe extends MiniGame {

    public static void returnPoints(Player player) {
        int pointsToAdd = 40;
        System.out.println("You obtained: " + pointsToAdd + " points.");
        player.getPoints().addPoints(pointsToAdd);
    }

    public boolean play(Player p) {
        Scanner sc = new Scanner(System.in);
        char[][] gameBoard = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        char player = 'X';
        char computer = 'O';

        System.out.println("Welcome to the Tic Tac Toe game.");

        while (true) {
            playerMove(gameBoard, sc, player, p);
            if (isGameFinished(gameBoard, player, p)) {
                break;
            }
            printBoard(gameBoard);

            computerMove(gameBoard, computer);
            if (isGameFinished(gameBoard, computer, p)) {
                break;
            }
            printBoard(gameBoard);
        }
        return false;
    }

    private static void printBoard(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.println("----------");
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print("| " + gameBoard[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("----------");
    }

    private static void playerMove(char[][] gameBoard, Scanner sc, char player, Player p) {
        int row;
        int col;
        while (true) {
            System.out.println("Enter your move (row [1-3] and column [1-3]): ");
            row = sc.nextInt() - 1;
            col = sc.nextInt() - 1;
            if (row < 0 || col < 0 || row >= gameBoard.length || col >= gameBoard[0].length) {
                System.out.println("This move is out of bounds");
            } else if (gameBoard[row][col] != ' ') {
                System.out.println("This position is already taken");
            } else {
                break;
            }
        }
        gameBoard[row][col] = player;
    }

    private static void computerMove(char[][] gameBoard, char computer) {
        Random rand = new Random();
        int row;
        int col;
        while (true) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            if (gameBoard[row][col] == ' ') {
                break;
            }
        }
        gameBoard[row][col] = computer;
        System.out.println("Computer chose position: " + (row + 1) + " " + (col + 1));
    }

    private static boolean isGameFinished(char[][] gameBoard, char player, Player playerObj) {
        if (hasContestantWon(gameBoard, player)) {
            printBoard(gameBoard);
            System.out.println(player + " wins!");
            if (player == 'X') {
                returnPoints(playerObj);
                if(playerObj.getPerkUtil().hasPerkDoubleRoll()){
                    System.out.println("You already have a 'Double Roll' perk. No perk will be assigned.");
                }else{
                    System.out.println("You obtained a 'Double Roll' perk");
                    playerObj.getPerkUtil().setPerkDoubleRoll(true);
                }
            }
            return true;
        }
        if (isBoardFull(gameBoard)) {
            printBoard(gameBoard);
            System.out.println("The game is a tie!");
            return true;
        }
        return false;
    }

    private static boolean isBoardFull(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasContestantWon(char[][] gameBoard, char player) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == player && gameBoard[i][1] == player && gameBoard[i][2] == player) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == player && gameBoard[1][i] == player && gameBoard[2][i] == player) {
                return true;
            }
        }
        if (gameBoard[0][0] == player && gameBoard[1][1] == player && gameBoard[2][2] == player) {
            return true;
        }
        return gameBoard[0][2] == player && gameBoard[1][1] == player && gameBoard[2][0] == player;
    }
}