package it.unibz.pp2024.LudoKing.gameLogic.config;

import it.unibz.pp2024.LudoKing.user.Player;

import java.util.List;
import java.util.Scanner;

public class Chat {
    private static List<Player> playersInGame = List.of();

    public Chat(List<Player> players) {
        playersInGame = players;
    }

    public static void startChat(Player currentPlayer) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chat started. Type 'exit' to leave the chat.");
        String message;

        do {
            System.out.print(currentPlayer.getName() + ": ");
            message = sc.nextLine();
            if (!message.equalsIgnoreCase("exit")) {
                broadcastMessage(currentPlayer, message);
            }
        } while (!message.equalsIgnoreCase("exit"));
    }

    private static void broadcastMessage(Player sender, String message) {
        playersInGame.stream()
                .filter(player -> !player.equals(sender))
                .forEach(player -> System.out.println(player.getName() + " received: " + sender.getName() + " says: " + message));
    }
}
