package it.unibz.pp2024.LudoKing.MiniGames;

import java.util.Scanner;
public class TicTacToe {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
        int rows=2, columns=2;
        char[][] game=new char[2][2];
//		for(int i=0;i<game.length;i++) {
//				System.out.println("|");
//			for(int j=0;j<game[i].length;j++) {
//				System.out.print("_");
//			}
//		}

        System.out.println("Welcome to the Tic Tac Toe game.");
        char playerOne='X';
        char playerTwo='O';


        System.out.println("Enter the row");
        int choiceRow=sc.nextInt();
        System.out.println("Enter the column");
        int choiceCol=sc.nextInt();
        for(int i =0; i<(game.length)+1; i++)
        {
            System.out.println("----------");
            for(int j =0; j<(game.length+1);j++)
            {
                System.out.print("| ");
//				System.out.print(game[i][j] + " ");
                System.out.print(" ");
            }
            System.out.println("| ");
        }
        System.out.println("----------");

    }
}
