package ludo;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME TO LUDO GAME\n");
		System.out.println("How many players are there? ");
		int nPlayers = sc.nextInt();
		if (nPlayers < 2 || nPlayers > 4) {
			System.out.println("Select a valid number of players (2, 3 or 4");
			// complete this if sentence
		}
		System.out.println("Choose a username: ");
	}

}
